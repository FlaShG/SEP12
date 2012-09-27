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

public class BefehlFuettereTest
{
	BefehlFuettere fuetter = new BefehlFuettere();
	Raum raum = new Raum("Center", "blubb");
	Raum zielraum = new Raum("ziel", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Befehlszeile befehlszeile = new Befehlszeile("füttere");
	Inventar inventar = new Inventar();
	Katze katze;
	Maus maus = new Maus(raum, zielraum);
	String[] moeglicheRichtung = new String[] { "west", "süd", "ost", "nord" };
	String[] moeglicheRichtung1 = new String[] { "nord" };

	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.UKuchen);
		inventar.fuegeItemHinzu(Item.UKuchen);
		spieler.setInventar(inventar);
		katze = new Katze(raum);

		raum.setKatze(katze);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raum);
	}

	@Test
	public void testBestimmeRichtung()
	{
		assertEquals("süd", BefehlFuettere.bestimmeRichtung(Item.IKuchen,
				"süd", moeglicheRichtung));
		assertEquals("süd", BefehlFuettere.bestimmeRichtung(Item.UKuchen,
				"süd", moeglicheRichtung));
		assertTrue("süd" != BefehlFuettere.bestimmeRichtung(Item.IGiftkuchen,
				"süd", moeglicheRichtung));
		assertTrue("süd" != BefehlFuettere.bestimmeRichtung(Item.UGiftkuchen,
				"süd", moeglicheRichtung));
		assertEquals(null, BefehlFuettere.bestimmeRichtung(Item.Keins, "süd",
				moeglicheRichtung));

		assertEquals("nord", BefehlFuettere.bestimmeRichtung(Item.IKuchen,
				"nord", moeglicheRichtung1));
		assertEquals("nord", BefehlFuettere.bestimmeRichtung(Item.UKuchen,
				"nord", moeglicheRichtung1));
		assertEquals("nord", BefehlFuettere.bestimmeRichtung(Item.IGiftkuchen,
				"nord", moeglicheRichtung1));
		assertEquals("nord", BefehlFuettere.bestimmeRichtung(Item.UGiftkuchen,
				"nord", moeglicheRichtung1));
		assertEquals(null, BefehlFuettere.bestimmeRichtung(Item.Keins, "nord",
				moeglicheRichtung1));

	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(fuetter.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		raum.setKatze(null);
		raum.setMaus(maus);
		assertTrue(fuetter.vorbedingungErfuellt(kontext, spieler, befehlszeile));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(fuetter.ausfuehren(kontext, spieler, befehlszeile));
		raum.setKatze(null);
		raum.setMaus(maus);
		assertTrue(fuetter.ausfuehren(kontext, spieler, befehlszeile));
	}

	@Test
	public void testFuettereTierMit()
	{
		assertTrue(BefehlFuettere.fuettereTierMit(kontext, spieler,
				Item.IKuchen));
		raum.setKatze(null);
		assertFalse(BefehlFuettere.fuettereTierMit(kontext, spieler,
				Item.IKuchen));
		raum.setMaus(maus);
		assertTrue(BefehlFuettere.fuettereTierMit(kontext, spieler,
				Item.IKuchen));
	}

	@Test
	public void testFuettereMaus()
	{
		assertTrue(BefehlFuettere.fuettereMaus(kontext, spieler, Item.IKuchen,
				raum, maus));
		assertTrue(BefehlFuettere.fuettereMaus(kontext, spieler, Item.UKuchen,
				raum, maus));
		assertTrue(BefehlFuettere.fuettereMaus(kontext, spieler,
				Item.IGiftkuchen, raum, maus));
		assertTrue(BefehlFuettere.fuettereMaus(kontext, spieler,
				Item.UGiftkuchen, raum, maus));
		assertTrue(BefehlFuettere.fuettereMaus(kontext, spieler, Item.Keins,
				raum, maus));

	}

	@Test
	public void testFuettereKatze()
	{
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.IKuchen));
		katze = new Katze(raum);
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.UKuchen));
		katze = new Katze(raum);
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.IGiftkuchen));
		katze = new Katze(raum);
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.UGiftkuchen));
		katze = new Katze(raum);
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.Keins));

	}

	@Test
	public void testGibFehlerAus()
	{
		Inventar inventar3 = new Inventar();
		spieler.setInventar(inventar3);
		fuetter.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.MAUS_KEIN_KRUEMEL + "\n",
				kontext.getNachrichtFuer(spieler));

		spieler.getInventar().fuegeItemHinzu(Item.IKuchen);
		raum.setKatze(null);
		raum.setMaus(null);
		fuetter.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN
				+ "\n", kontext.getNachrichtFuer(spieler));

		raum.setKatze(katze);
		assertTrue(BefehlFuettere.fuettereKatze(kontext, spieler, katze,
				Item.IKuchen));
		assertTrue(katze.isSatt());
		assertEquals(TextVerwalter.KATZE_IST_SATT_GEWORDEN + "\n",
				kontext.getNachrichtFuer(spieler));
		fuetter.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.KATZE_HAT_KEINEN_HUNGER + "\n",
				kontext.getNachrichtFuer(spieler));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("füttere", fuetter.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_FEED, fuetter.getHilfe());
	}

}
