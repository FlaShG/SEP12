package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

//TODO: Stussiger Test ist stussig
public class BefehlEssenTest
{
	BefehlEssen essen = new BefehlEssen();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("essen");

	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void testAusfuehren()
	{
		assertFalse(essen.ausfuehren(kontext, spieler, befehlszeile));
	}

	@Test
	public void testGetBefehlsname()
	{
		assertEquals(TextVerwalter.BEFEHL_ESSEN, essen.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_EAT, essen.getHilfe());

	}

	@Test
	public void testGibFehlerAus()
	{
		essen.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.KEINORT, kontext.getNachrichtFuer(spieler)
				.substring(0, TextVerwalter.KEINORT.length()));
	}

	@Test
	public void testVorbedingungErfuellt()
	{

		assertFalse(essen.vorbedingungErfuellt(kontext, spielerleeresInventar,
				befehlszeile));
	}

}
