package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Katze;
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
	Befehlszeile fuettereK = new Befehlszeile("fütter krümel");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Inventar inventar3 = new Inventar();
	Katze katze;
	Maus maus;

	@Before
	public void setUp() throws Exception
	{
		spieler.setLebensEnergie(10);
		inventar.fuegeItemHinzu(Item.UGiftkuchen);
		inventar.fuegeItemHinzu(Item.UKuchen);
		inventar2.fuegeItemHinzu(Item.IGiftkuchen);
		inventar2.fuegeItemHinzu(Item.IKuchen);
		spieler.setInventar(inventar);
		katze = new Katze(raumC);
		raumC.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumO);
		raumO.setAusgang(TextVerwalter.RICHTUNG_WESTEN, raumC);
		maus = new Maus(raumC, raumO);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		spieler.setInventar(inventar3);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
		spieler.setInventar(inventar);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
		spieler.setInventar(inventar3);
		raumC.setKatze(katze);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
		spieler.setInventar(inventar);
		assertTrue(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
		spieler.setInventar(inventar3);
		raumC.setKatze(null);
		raumC.setMaus(maus);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
		spieler.setInventar(inventar2);
		assertFalse(futterK.vorbedingungErfuellt(kontext, spieler, fuettereK));
	}

	@Test
	public void testAusfuehren()
	{
		assertFalse(futterK.ausfuehren(kontext, spieler, fuettereK));
		raumC.setKatze(katze);
		spieler.setInventar(inventar);
		assertTrue(futterK.ausfuehren(kontext, spieler, fuettereK));
		raumC.setKatze(null);
		raumC.setMaus(maus);
		spieler.setInventar(inventar2);
		assertTrue(futterK.ausfuehren(kontext, spieler, fuettereK));
	}

	@Test
	public void testGibFehlerAus()
	{
		futterK.gibFehlerAus(kontext, spieler, fuettereK);
		assertEquals(TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN
				+ "\n", kontext.getNachrichtFuer(spieler));
		spieler.setInventar(inventar);
		raumC.setKatze(katze);
		futterK.ausfuehren(kontext, spieler, fuettereK);
		futterK.gibFehlerAus(kontext, spieler, fuettereK);
		assertEquals(TextVerwalter.KATZE_STIRBT + "\n"
				+ TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN + "\n",
				kontext.getNachrichtFuer(spieler));
		raumC.setKatze(null);
		raumC.setMaus(maus);
		futterK.ausfuehren(kontext, spieler, fuettereK);
		assertEquals(
				TextVerwalter.MAUS_RICHTUNGSANGABE.replace("%s",
						TextVerwalter.RICHTUNG_OSTEN) + "\n",
				kontext.getNachrichtFuer(spieler));
		spieler.setInventar(inventar2);
		futterK.gibFehlerAus(kontext, spieler, fuettereK);
		assertEquals(TextVerwalter.KEIN_KUCHEN_DIESER_ART + "\n",
				kontext.getNachrichtFuer(spieler));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_FUETTERE + " krümel",
				futterK.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_FEED, futterK.getHilfe());
	}

}
