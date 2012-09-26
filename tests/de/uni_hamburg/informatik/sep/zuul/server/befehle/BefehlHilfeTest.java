package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlHilfeTest
{
	BefehlHilfe hilfe = new BefehlHilfe();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurHilfe = new Befehlszeile("hilfe");
	Befehlszeile zweiMalHilfe = new Befehlszeile("hilfe hilfe");

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(hilfe.ausfuehren(kontext, spieler, nurHilfe));
		assertTrue(hilfe.ausfuehren(kontext, spieler, zweiMalHilfe));
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(hilfe.vorbedingungErfuellt(kontext, spieler, nurHilfe));
	}

	@Test
	public void testGibFehlerAus()
	{
		//TODO Fehlerausgabe beim laden Test
		hilfe.gibFehlerAus(kontext, spieler, nurHilfe);
		//		assertEquals(
		//				TextVerwalter.***,
		//				kontext.getNachrichtFuer(spieler).substring(0,
		//						TextVerwalter.***.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_HILFE, hilfe.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_HELP, hilfe.getHilfe());
	}

}
