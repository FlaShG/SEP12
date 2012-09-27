package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class BefehlGibMirMehrLebenTest
{
	BefehlGibMirMehrLeben mehrLeben = new BefehlGibMirMehrLeben();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile gibMirMehrLeben = new Befehlszeile("gib mir mehr leben");

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(mehrLeben.vorbedingungErfuellt(kontext, spieler,
				gibMirMehrLeben));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(mehrLeben.ausfuehren(kontext, spieler, gibMirMehrLeben));
		assertEquals(spieler.getLebensEnergie(), 100);
		assertEquals(
				"Schwupp.",
				kontext.getNachrichtFuer(spieler).substring(0,
						"Schwupp.".length()));
		assertTrue(mehrLeben.ausfuehren(kontext, spieler, gibMirMehrLeben));
		assertEquals(spieler.getLebensEnergie(), 100);
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("gib mir mehr leben", mehrLeben.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals("Hey ho, I'm a cheat code", mehrLeben.getHilfe());
	}

}
