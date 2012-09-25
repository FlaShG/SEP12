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

public class BefehlAblegenGuterKruemelTest
{
	BefehlAblegenGuterKruemel ablegen = new BefehlAblegenGuterKruemel();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("ablegen guter krümel");

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
		assertTrue(spieler.getInventar().has(Item.IKuchen));
		assertTrue(ablegen.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(Item.UKuchen, raum.getNaechstesItem());
		assertFalse(spieler.getInventar().has(Item.IKuchen));
		assertEquals(TextVerwalter.ABLEGEN_TEXT,
				kontext.getNachrichtFuer(spieler).substring(0, 26));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("ablegen guter krümel", ablegen.getBefehlsnamen()[0]);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		Spieler spieler3 = new Spieler("nurUkuchen");
		Inventar inventar3 = new Inventar();
		inventar3.fuegeItemHinzu(Item.UKuchen);
		spieler3.setInventar(inventar3);

		assertTrue(ablegen.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		assertFalse(ablegen.vorbedingungErfuellt(kontext,
				spielerleeresInventar, befehlszeile));
		assertFalse(ablegen.vorbedingungErfuellt(kontext, spieler3,
				befehlszeile));
	}

	@Test
	public void testGibFehlerAus()
	{
		ablegen.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.NICHTS_ZUM_ABLEGEN, kontext
				.getNachrichtFuer(spieler).substring(0, 31));
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_ABLEGEN, ablegen.getHilfe());
	}

}
