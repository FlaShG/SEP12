package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlEssenTascheGuterKruemelTest
{
	BefehlEssenTascheGuterKruemel essentasche = new BefehlEssenTascheGuterKruemel();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spieler2 = new Spieler("peter");
    Inventar inventar = new Inventar();
    Inventar inventar2 = new Inventar();
    Befehlszeile befehlszeile= new Befehlszeile("essen tasche guter krümel");
    
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
	public void testAusfuehren()
	{
	assertTrue(spieler.getInventar().has(Item.IKuchen));
	assertTrue(essentasche.ausfuehren(kontext, spieler, befehlszeile));
	assertFalse(spieler.getInventar().has(Item.IKuchen));
	
	assertFalse(spieler2.getInventar().has(Item.IKuchen));
	assertTrue(essentasche.ausfuehren(kontext, spieler2, befehlszeile));
	assertFalse(spieler2.getInventar().has(Item.IKuchen));
	}

	@Test
	public void testVorbedingungErfuellt()
	{
         assertTrue(essentasche.vorbedingungErfuellt(kontext, spieler, befehlszeile));
         assertFalse(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
         spieler2.getInventar().fuegeItemHinzu(Item.UKuchen);
         assertFalse(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
         spieler2.getInventar().fuegeItemHinzu(Item.IGiftkuchen);
         assertFalse(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
         spieler2.getInventar().fuegeItemHinzu(Item.UGiftkuchen);
         assertFalse(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
         spieler2.getInventar().fuegeItemHinzu(Item.IKuchen);
         assertTrue(essentasche.vorbedingungErfuellt(kontext, spieler2, befehlszeile));
	}

	@Test
	public void testGibFehlerAus()
	{
		essentasche.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.KEINIDENTIFIZIERTERKUCHEN, kontext.getNachrichtFuer(spieler).substring(0, TextVerwalter.KEINIDENTIFIZIERTERKUCHEN.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals( "essen tasche guter krümel", essentasche.getBefehlsnamen()[0]);
	}

}
