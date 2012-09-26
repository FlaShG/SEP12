package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlNehmenTest
{
	BefehlNehmen nehmen = new BefehlNehmen();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurnehmen = new Befehlszeile("nehmen");
	Inventar inventar = new Inventar();

	@Before
	public void setUp() throws Exception
	{
		Stack<Item> stack = new Stack<Item>();
		stack.push(Item.UKuchen);
		stack.push(Item.Gegengift);
		stack.push(Item.IGiftkuchen);
		stack.push(Item.IKuchen);
		raumC.setItems(stack);
		spieler.setInventar(inventar);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(nehmen.vorbedingungErfuellt(kontext, spieler, nurnehmen));
		raumC.loescheItem();
		raumC.loescheItem();
		raumC.loescheItem();
		raumC.loescheItem();
		assertFalse(nehmen.vorbedingungErfuellt(kontext, spieler, nurnehmen));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(nehmen.ausfuehren(kontext, spieler, nurnehmen));
		assertTrue(nehmen.ausfuehren(kontext, spieler, nurnehmen));
		assertTrue(nehmen.ausfuehren(kontext, spieler, nurnehmen));
		assertTrue(nehmen.ausfuehren(kontext, spieler, nurnehmen));
		Stack<Item> stack = new Stack<Item>();
		raumC.setItems(stack);
		assertTrue(nehmen.ausfuehren(kontext, spieler, nurnehmen));
	}

	@Test
	public void testGibFehlerAus()
	{
		nehmen.gibFehlerAus(kontext, spieler, nurnehmen);
		assertEquals(
				TextVerwalter.NICHTSZUMNEHMENTEXT,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.NICHTSZUMNEHMENTEXT.length()));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_NEHMEN, nehmen.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_TAKE, nehmen.getHilfe());
	}

}
