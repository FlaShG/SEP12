package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTascheTest
{
	BefehlEssenTasche essentasche = new BefehlEssenTasche();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spieler2 = new Spieler("peter");
    Inventar inventar = new Inventar();
    Inventar inventar2 = new Inventar();
    Befehlszeile befehlszeile= new Befehlszeile("essen tasche");
    
    SpielLogik spiellogi = new SpielLogik();
    
    
    
	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.UGiftkuchen);
		inventar.fuegeItemHinzu(Item.UKuchen);
		inventar.fuegeItemHinzu(Item.IGiftkuchen);
		inventar.fuegeItemHinzu(Item.IKuchen);
		spieler.setInventar(inventar);
		spieler2.setInventar(inventar2);
		spieler.setLebensEnergie(10);
		spieler2.setLebensEnergie(1);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(essentasche.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		assertFalse(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
	}

	@Test
	public void testAusfuehren()
	{
		assertEquals(Item.IKuchen, spieler.getInventar().nehmeLetztesItem());
		assertTrue(essentasche.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(13, spieler.getLebensEnergie());
		
		
	}

	@Test
	public void testEsseKuchen()
	{
		int energie = spieler.getLebensEnergie();
		essentasche.esseKuchen(kontext, spieler, Item.UKuchen);
		assertEquals(13, spieler.getLebensEnergie());
		essentasche.esseKuchen(kontext, spieler, Item.UGiftkuchen);
		assertEquals(12, spieler.getLebensEnergie());
		essentasche.esseKuchen(kontext, spieler, Item.IKuchen);
		assertEquals(15, spieler.getLebensEnergie());
		essentasche.esseKuchen(kontext, spieler, Item.IGiftkuchen);
		assertEquals(14, spieler.getLebensEnergie());
		assertTrue(essentasche.esseKuchen(kontext, spieler2, Item.IGiftkuchen));
		assertEquals(0, spieler2.getLebensEnergie());
		assertEquals(TextVerwalter.KUCHENTODTEXT,  kontext.getNachrichtFuer(spieler2).substring(0, TextVerwalter.KUCHENTODTEXT.length()));
		

		
	}

	@Test
	public void testGibFehlerAus()
	{
		essentasche.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.NICHTSZUMESSENTEXT, kontext.getNachrichtFuer(spieler).substring(0, TextVerwalter.NICHTSZUMESSENTEXT.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals( "essen tasche", essentasche.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_EAT, essentasche.getHilfe());
	}

}
