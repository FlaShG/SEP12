package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlLadenTest
{

	BefehlLaden laden = new BefehlLaden();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurladen = new Befehlszeile("laden");

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(laden.vorbedingungErfuellt(kontext, spieler, nurladen));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(laden.ausfuehren(kontext, spieler, nurladen));
	}

	@Test
	public void testGibFehlerAus()
	{
		//TODO Fehlerausgabe beim laden Test
		laden.gibFehlerAus(kontext, spieler, nurladen);
		//		assertEquals(
		//				TextVerwalter.***,
		//				kontext.getNachrichtFuer(spieler).substring(0,
		//						TextVerwalter.***.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_LADEN, laden.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_LOAD, laden.getHilfe());
	}

}
