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

public class BefehlBeendenTest
{
	BefehlBeenden beenden = new BefehlBeenden();
	Raum raum = new Raum("bla", "blubb");
	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("beenden");

	@Before
	public void setUp() throws Exception
	{
		spieler.setLebensEnergie(100);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(beenden.vorbedingungErfuellt(kontext, spieler, befehlszeile));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(spieler.getLebensEnergie() != 0);
		assertTrue(beenden.ausfuehren(kontext, spieler, befehlszeile));
		assertEquals(spieler.getLebensEnergie(), 0);

		assertEquals(
				TextVerwalter.BEENDENTEXT,
				kontext.getNachrichtFuer(spieler).substring(0,
						TextVerwalter.BEENDENTEXT.length()));
	}

	@Test
	public void testGibFehlerAus()
	{
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("beenden", beenden.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_QUIT, beenden.getHilfe());
	}

}
