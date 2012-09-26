package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlSchauenTest
{
	BefehlSchauen schauen = new BefehlSchauen();
	Raum raumC = new Raum("Center", "blubb");
	Raum raumN = new Raum("Nord", "blubb");
	Raum raumO = new Raum("Ost", "blubb");
	Raum raumS = new Raum("Süd", "blubb");
	Raum raumW = new Raum("West", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile befehlszeileN = new Befehlszeile("schaue nord");
	Befehlszeile befehlszeileO = new Befehlszeile("schaue ost");
	Befehlszeile befehlszeileS = new Befehlszeile("schaue süd");
	Befehlszeile befehlszeileW = new Befehlszeile("schaue west");
	Befehlszeile falsch = new Befehlszeile("schaue falsch");
	Befehlszeile nurschauen = new Befehlszeile("schaue");
	Katze katze;
	Maus maus;

	@Before
	public void setUp() throws Exception
	{
		raumC.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, raumS);
		raumC.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumO);
		raumC.setAusgang(TextVerwalter.RICHTUNG_NORDEN, raumN);
		raumC.setAusgang(TextVerwalter.RICHTUNG_WESTEN, raumW);
		Stack<Item> stack = new Stack<Item>();
		stack.push(Item.UKuchen);
		stack.push(Item.Gegengift);
		stack.push(Item.IGiftkuchen);
		stack.push(Item.IKuchen);
		raumN.setItems(stack);
		katze = new Katze(raumW);
		raumW.setKatze(katze);
		maus = new Maus(raumO, raumW);
		raumO.setMaus(maus);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertFalse(schauen.vorbedingungErfuellt(kontext, spieler, nurschauen));
		assertFalse(schauen.vorbedingungErfuellt(kontext, spieler, falsch));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileO));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileS));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileW));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileN));
	}

	@Test
	public void testExtrahiereRichtung()
	{
		assertEquals(TextVerwalter.RICHTUNG_NORDEN,
				schauen.extrahiereRichtung(befehlszeileN));
		assertEquals(TextVerwalter.RICHTUNG_OSTEN,
				schauen.extrahiereRichtung(befehlszeileO));
		assertEquals(TextVerwalter.RICHTUNG_WESTEN,
				schauen.extrahiereRichtung(befehlszeileW));
		assertEquals(TextVerwalter.RICHTUNG_SUEDEN,
				schauen.extrahiereRichtung(befehlszeileS));
		assertNull(schauen.extrahiereRichtung(falsch));
	}

	@Test
	public void testAusfuehren()
	{
		assertFalse(schauen.vorbedingungErfuellt(kontext, spieler, nurschauen));
		assertFalse(schauen.vorbedingungErfuellt(kontext, spieler, falsch));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileO));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileS));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileW));
		assertTrue(schauen.ausfuehren(kontext, spieler, befehlszeileN));
	}

	@Test
	public void testGibFehlerAus()
	{
		schauen.gibFehlerAus(kontext, spieler, falsch);
		assertEquals(
				TextVerwalter.KEINRAUMZUMSCHAUN,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.KEINRAUMZUMSCHAUN.length()));
		schauen.gibFehlerAus(kontext, spieler, nurschauen);
		assertEquals(
				TextVerwalter.KEINESCHAURICHTUNG,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.KEINESCHAURICHTUNG.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_SCHAUEN, schauen.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_LOOK, schauen.getHilfe());
	}

}
