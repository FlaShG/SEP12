package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

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
	String[] moeglicheRichtung = new String[] {"west", "süd", "ost", "nord"};
	String[] moeglicheRichtung1 = new String[] {"nord"};
	
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
		assertEquals("süd",fuetter.bestimmeRichtung(Item.IKuchen, "süd", moeglicheRichtung)); 
		assertEquals("süd",fuetter.bestimmeRichtung(Item.UKuchen, "süd", moeglicheRichtung)); 
		assertTrue("süd"!= fuetter.bestimmeRichtung(Item.IGiftkuchen, "süd", moeglicheRichtung));
		assertTrue("süd"!= fuetter.bestimmeRichtung(Item.UGiftkuchen, "süd", moeglicheRichtung));
		assertEquals(null, fuetter.bestimmeRichtung(Item.Keins, "süd", moeglicheRichtung));
		
		assertEquals("nord",fuetter.bestimmeRichtung(Item.IKuchen, "nord", moeglicheRichtung1)); 
		assertEquals("nord",fuetter.bestimmeRichtung(Item.UKuchen, "nord", moeglicheRichtung1)); 
		assertEquals("nord", fuetter.bestimmeRichtung(Item.IGiftkuchen, "nord", moeglicheRichtung1));
		assertEquals("nord", fuetter.bestimmeRichtung(Item.UGiftkuchen, "nord", moeglicheRichtung1));
		assertEquals(null, fuetter.bestimmeRichtung(Item.Keins, "nord", moeglicheRichtung1));
		
	
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
		assertTrue(fuetter.fuettereTierMit(kontext, spieler, Item.IKuchen));
		 raum.setKatze(null);
		assertFalse(fuetter.fuettereTierMit(kontext, spieler, Item.IKuchen));
		 raum.setMaus(maus);
		assertTrue(fuetter.fuettereTierMit(kontext, spieler, Item.IKuchen));
	}

	@Test
	public void testFuettereMaus()
	{
		//TODO: Test fail("Not yet implemented");
	}

	@Test
	public void testFuettereKatze()
	{
		//TODO: Test fail("Not yet implemented");
	}

	@Test
	public void testGibFehlerAus()
	{
		//TODO: Test fail("Not yet implemented");
	}

	@Test
	public void testGetBefehlsnamen()
	{
		//TODO: Test fail("Not yet implemented");
	}

	@Test
	public void testGetHilfe()
	{
		//TODO: Test fail("Not yet implemented");
	}

}
