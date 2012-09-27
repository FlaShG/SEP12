package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlGeheTest
{
	BefehlGehe gehen = new BefehlGehe();
	Raum raumC = new Raum("Center", "blubb");
	Raum raumN = new Raum("Nord", "blubb");
	Raum raumO = new Raum("Ost", "blubb");
	Raum raumS = new Raum("Süd", "blubb");
	Raum raumW = new Raum("West", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile befehlszeileN = new Befehlszeile("gehe nord");
	Befehlszeile befehlszeileO = new Befehlszeile("gehe ost");
	Befehlszeile befehlszeileS = new Befehlszeile("gehe süd");
	Befehlszeile befehlszeileW = new Befehlszeile("gehe west");
	Befehlszeile falsch = new Befehlszeile("falsch");
	Befehlszeile nurgehen = new Befehlszeile("gehen");
	Befehlszeile kurzN = new Befehlszeile("n");
	Befehlszeile kurzO = new Befehlszeile("o");
	Befehlszeile kurzS = new Befehlszeile("s");
	Befehlszeile kurzW = new Befehlszeile("w");

	@Before
	public void setUp() throws Exception
	{
		raumC.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, raumS);
		raumS.setAusgang(TextVerwalter.RICHTUNG_NORDEN, raumC);
		raumC.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumO);
		raumO.setAusgang(TextVerwalter.RICHTUNG_WESTEN, raumC);
		raumC.setAusgang(TextVerwalter.RICHTUNG_NORDEN, raumN);
		raumN.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, raumC);
		raumC.setAusgang(TextVerwalter.RICHTUNG_WESTEN, raumW);
		raumW.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumC);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(gehen.ausfuehren(kontext, spieler, befehlszeileN));
		assertEquals(raumN, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, kurzS));
		assertEquals(raumC, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, befehlszeileO));
		assertEquals(raumO, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, kurzW));
		assertEquals(raumC, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, befehlszeileS));
		assertEquals(raumS, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, kurzN));
		assertEquals(raumC, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, befehlszeileW));
		assertEquals(raumW, kontext.getAktuellenRaumZu(spieler));
		assertTrue(gehen.ausfuehren(kontext, spieler, kurzO));
		assertEquals(raumC, kontext.getAktuellenRaumZu(spieler));
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertFalse(gehen.vorbedingungErfuellt(kontext, spieler, falsch));
		assertFalse(gehen.vorbedingungErfuellt(kontext, spieler, nurgehen));
		assertTrue(gehen.vorbedingungErfuellt(kontext, spieler, befehlszeileN));
	}

	@Test
	public void testGibFehlerAus()
	{
		gehen.gibFehlerAus(kontext, spieler, nurgehen);
		assertEquals(
				TextVerwalter.KEINERICHTUNG,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.KEINERICHTUNG.length()));
		gehen.ausfuehren(kontext, spieler, befehlszeileN);
		gehen.gibFehlerAus(kontext, spieler, befehlszeileN);
		assertEquals(
				TextVerwalter.DAISTKEINRAUM,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.DAISTKEINRAUM.length()));

	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("n", gehen.getBefehlsnamen()[0]);
		assertEquals("w", gehen.getBefehlsnamen()[1]);
		assertEquals("s", gehen.getBefehlsnamen()[2]);
		assertEquals("o", gehen.getBefehlsnamen()[3]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " n",
				gehen.getBefehlsnamen()[4]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " w",
				gehen.getBefehlsnamen()[5]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " s",
				gehen.getBefehlsnamen()[6]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " o",
				gehen.getBefehlsnamen()[7]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " "
				+ TextVerwalter.RICHTUNG_NORDEN, gehen.getBefehlsnamen()[8]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " "
				+ TextVerwalter.RICHTUNG_WESTEN, gehen.getBefehlsnamen()[9]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " "
				+ TextVerwalter.RICHTUNG_SUEDEN, gehen.getBefehlsnamen()[10]);
		assertEquals(TextVerwalter.BEFEHL_GEHEN + " "
				+ TextVerwalter.RICHTUNG_OSTEN, gehen.getBefehlsnamen()[11]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_GO, gehen.getHilfe());
	}

}
