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

public class BefehlEssenBodenTest
{

	BefehlEssenBoden essenboden = new BefehlEssenBoden();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("essen boden");

	@Before
	public void setUp() throws Exception
	{
		raum.addItem(Item.UKuchen);
		//		raum.addItem(Item.UGiftkuchen);
		//		raum.addItem(Item.IKuchen);
		//		raum.addItem(Item.IGiftkuchen);
		spieler.setLebensEnergie(8);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raum);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(essenboden.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
		raum.loescheItem();
		assertFalse(essenboden.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(raum.getItems().contains(Item.UKuchen));
		assertEquals(8, spieler.getLebensEnergie());
		assertTrue(essenboden.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(11, spieler.getLebensEnergie());

		raum.addItem(Item.UGiftkuchen);
		raum.addItem(Item.UGiftkuchen);
		assertTrue(raum.getItems().contains(Item.UGiftkuchen));
		assertEquals(11, spieler.getLebensEnergie());
		assertTrue(essenboden.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(10, spieler.getLebensEnergie());
	}

	@Test
	public void testGibFehlerAus()
	{
		essenboden.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(
				TextVerwalter.NICHTSZUMESSENTEXTBODEN,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.NICHTSZUMESSENTEXTBODEN.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("essen boden", essenboden.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_EAT, essenboden.getHilfe());
	}

}
