package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlAblegenKruemelTest
{

	BefehlAblegenKruemel ablegen = new BefehlAblegenKruemel();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
    Inventar inventar = new Inventar();
    Inventar inventar2 = new Inventar();
    Befehlszeile befehlszeile= new Befehlszeile("ablegen krümel");
    
	@Before
	public void setUp() throws Exception
	{
		  kontext.setAktuellenRaumZu(spieler, raum);
		   inventar.fuegeItemHinzu(Item.UKuchen);
		   inventar.fuegeItemHinzu(Item.UGiftkuchen);
		   inventar.fuegeItemHinzu(Item.IKuchen);
     	   inventar.fuegeItemHinzu(Item.IGiftkuchen);
		   spieler.setInventar(inventar);
		   spielerleeresInventar.setInventar(inventar2);
		     
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(spieler.getInventar().has(Item.UKuchen)||spieler.getInventar().has(Item.UGiftkuchen));
		assertTrue(ablegen.ausfuehren(kontext, spieler, befehlszeile));
		assertTrue((raum.getNaechstesItem() == Item.UGiftkuchen) || (raum.getNaechstesItem() == Item.UKuchen));
		assertFalse(spieler.getInventar().has(Item.UKuchen) && spieler.getInventar().has(Item.UGiftkuchen));
		assertEquals(TextVerwalter.ABLEGEN_TEXT, kontext.getNachrichtFuer(spieler).substring(0, 26));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals( "ablegen krümel", ablegen.getBefehlsnamen()[0]);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		Spieler spieler3 = new Spieler("nurUkuchen");
		Spieler spieler4 = new Spieler("nurUGiftuchen");
		Spieler spieler5 = new Spieler("nurIKuchen");
		
	    Inventar inventar3 = new Inventar();
	    Inventar inventar4 = new Inventar();
	    Inventar inventar5 = new Inventar();
	    
	    inventar3.fuegeItemHinzu(Item.UKuchen);
		spieler3.setInventar(inventar3);
		inventar4.fuegeItemHinzu(Item.UGiftkuchen);
		spieler4.setInventar(inventar4);
		inventar5.fuegeItemHinzu(Item.IKuchen);
		spieler5.setInventar(inventar5);
			
		
		
		assertTrue(ablegen.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		assertFalse(ablegen.vorbedingungErfuellt(kontext, spielerleeresInventar, befehlszeile));
		assertTrue(ablegen.vorbedingungErfuellt(kontext, spieler3, befehlszeile));
		assertTrue(ablegen.vorbedingungErfuellt(kontext, spieler4, befehlszeile));
		assertFalse(ablegen.vorbedingungErfuellt(kontext, spieler5, befehlszeile));
	}

	@Test
	public void testGibFehlerAus()
	{
		ablegen.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.NICHTS_ZUM_ABLEGEN, kontext.getNachrichtFuer(spieler).substring(0, 31));
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_ABLEGEN, ablegen.getHilfe());
	}

}

