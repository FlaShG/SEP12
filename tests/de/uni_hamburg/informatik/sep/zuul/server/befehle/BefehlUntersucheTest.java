package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlUntersucheTest
{
	BefehlUntersuche untersuche = new BefehlUntersuche();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Inventar inventar = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("untersuche");

	@Before
	public void setUp() throws Exception
	{
		raum.setRaumart(RaumArt.Start);
		kontext.setAktuellenRaumZu(spieler, raum);
		inventar.fuegeItemHinzu(Item.UKuchen);
		inventar.fuegeItemHinzu(Item.UGiftkuchen);
		spieler.setInventar(inventar);
	}

	@Test
	public void testAusfuehren()
	{
		assertFalse(spieler.getInventar().has(Item.IKuchen));
		assertFalse(spieler.getInventar().has(Item.IGiftkuchen));
		assertTrue(untersuche.ausfuehren(kontext, spieler, befehlszeile));
		assertTrue(untersuche.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(Item.IKuchen, spieler.getInventar()
				.getKuchen(Item.IKuchen));
		assertEquals(Item.IGiftkuchen,
				spieler.getInventar().getKuchen(Item.IGiftkuchen));
		assertFalse(spieler.getInventar().has(Item.UKuchen));
		assertFalse(spieler.getInventar().has(Item.UGiftkuchen));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("untersuche", untersuche.getBefehlsnamen()[0]);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(untersuche.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
		spieler.getInventar().nehmeLetztesItem();
		spieler.getInventar().nehmeLetztesItem();
		assertFalse(untersuche.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
		raum.setRaumart(RaumArt.Normal);
		kontext.setAktuellenRaumZu(spieler, raum);
		assertFalse(untersuche.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
		spieler.setInventar(inventar);
		assertFalse(untersuche.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
	}

	@Test
	public void testGibFehlerAus()
	{
		untersuche.gibFehlerAus(kontext, spieler, befehlszeile);
		raum.setRaumart(RaumArt.Normal);
		kontext.setAktuellenRaumZu(spieler, raum);
		untersuche.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(
				TextVerwalter.BEFEHL_GIB_KEIN_OBJEKT,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.BEFEHL_GIB_KEIN_OBJEKT.length()));
		spieler.getInventar().nehmeLetztesItem();
		spieler.getInventar().nehmeLetztesItem();
		untersuche.gibFehlerAus(kontext, spieler, befehlszeile);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_GIVE, untersuche.getHilfe());
	}
}
