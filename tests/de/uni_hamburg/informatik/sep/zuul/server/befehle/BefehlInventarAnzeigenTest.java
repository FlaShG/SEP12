package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlInventarAnzeigenTest
{
	BefehlInventarAnzeigen inventarAnzeigen = new BefehlInventarAnzeigen();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurIventar = new Befehlszeile("iventar");
	Inventar inventar = new Inventar();

	@Before
	public void setUp() throws Exception
	{
		spieler.setInventar(inventar);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(inventarAnzeigen.vorbedingungErfuellt(kontext, spieler,
				nurIventar));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(inventarAnzeigen.ausfuehren(kontext, spieler, nurIventar));
	}

	@Test
	public void testGibFehlerAus()
	{
		//TODO Fehlerausgabe beim Inventar Test
		inventarAnzeigen.gibFehlerAus(kontext, spieler, nurIventar);
		//		assertEquals(
		//				TextVerwalter.***,
		//				kontext.getNachrichtFuer(spieler).substring(0,
		//						TextVerwalter.***T.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_INVENTAR,
				inventarAnzeigen.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_INVENTAR, inventarAnzeigen.getHilfe());
	}

}
