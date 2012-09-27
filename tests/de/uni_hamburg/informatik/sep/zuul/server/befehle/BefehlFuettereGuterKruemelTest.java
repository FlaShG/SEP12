package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettereGuterKruemelTest
{
	BefehlFuettereGuterKruemel futterGK = new BefehlFuettereGuterKruemel();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurfutter = new Befehlszeile("füttere guter krümel");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Inventar inventar3 = new Inventar();
	Katze katze;

	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.UKuchen);
		inventar.fuegeItemHinzu(Item.UGiftkuchen);
		inventar2.fuegeItemHinzu(Item.IKuchen);
		spieler.setInventar(inventar);
		katze = new Katze(raumC);
		raumC.setKatze(katze);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertFalse(futterGK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		spieler.setInventar(inventar3);
		assertFalse(futterGK.vorbedingungErfuellt(kontext, spieler, nurfutter));
	}

	@Test
	public void testAusfuehren()
	{
		spieler.setInventar(inventar2);
		assertTrue(futterGK.ausfuehren(kontext, spieler, nurfutter));
	}

	@Test
	public void testGibFehlerAus()
	{
		futterGK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.MAUS_KEIN_KRUEMEL + "\n",
				kontext.getNachrichtFuer(spieler));
		spieler.setInventar(inventar2);
		futterGK.ausfuehren(kontext, spieler, nurfutter);
		futterGK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.KATZE_IST_SATT_GEWORDEN + "\n"
				+ TextVerwalter.MAUS_KEIN_KRUEMEL + "\n",
				kontext.getNachrichtFuer(spieler));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_FUETTERE_GUT,
				futterGK.getBefehlsnamen()[0]);
	}

}
