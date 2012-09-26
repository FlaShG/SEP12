package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettereKruemelTest
{
	BefehlFuettereKruemel futterK = new BefehlFuettereKruemel();
	Raum raumC = new Raum("Center", "blubb");
	Raum raumO = new Raum("Ost", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurfutter = new Befehlszeile("fütter");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Inventar inventar3 = new Inventar();
	Katze katze;
	Maus maus;

	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.UKuchen);
		//		inventar.fuegeItemHinzu(Item.UGiftkuchen);
		inventar2.fuegeItemHinzu(Item.IKuchen);
		inventar2.fuegeItemHinzu(Item.IGiftkuchen);
		spieler.setInventar(inventar);
		katze = new Katze(raumC);
		raumC.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumO);
		maus = new Maus(raumC, raumO);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		spieler.setInventar(inventar3);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar3);
		raumC.setKatze(katze);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar);
		assertTrue(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar3);
		raumC.setKatze(null);
		raumC.setMaus(maus);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar);
		assertTrue(futterK.vorbedingungErfuellt(kontext, spieler, nurfutter));
	}

	@Test
	public void testAusfuehren()
	{
		assertFalse(futterK.ausfuehren(kontext, spieler, nurfutter));
		raumC.setKatze(katze);
		futterK.ausfuehren(kontext, spieler, nurfutter);
		assertFalse(futterK.ausfuehren(kontext, spieler, nurfutter));
		raumC.setKatze(null);
		raumC.setMaus(maus);
		spieler.setInventar(inventar);
		assertTrue(futterK.ausfuehren(kontext, spieler, nurfutter));
	}

	@Test
	public void testGibFehlerAus()
	{
		futterK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN
				+ "\n", kontext.getNachrichtFuer(spieler));
		spieler.setInventar(inventar2);
		raumC.setKatze(katze);
		futterK.ausfuehren(kontext, spieler, nurfutter);
		futterK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.KATZE_STIRBT + "\n"
				+ TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN + "\n",
				kontext.getNachrichtFuer(spieler));
		spieler.setInventar(inventar3);
		raumC.setKatze(null);
		raumC.setMaus(maus);
		futterK.ausfuehren(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.MAUS_RICHTUNGSANGABE.replace("%s", "null")
				+ "\n", kontext.getNachrichtFuer(spieler));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetHilfe()
	{
		fail("Not yet implemented");
	}

}
