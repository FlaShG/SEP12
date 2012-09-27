package de.uni_hamburg.informatik.sep.zuul.server.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BeinStellenTest
{
	BeinStellen beinS = new BeinStellen();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Spieler spieler2 = new Spieler("frans");
	Befehlszeile befehlszeile = new Befehlszeile("bein stellen");
	Befehlszeile falsch = new Befehlszeile("falsch");
	Befehlszeile kurzBs = new Befehlszeile("bs");

	@Before
	public void setUp() throws Exception
	{
		spieler.setLebensEnergie(10);
		spieler2.setLebensEnergie(10);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
		kontext.fuegeNeuenSpielerHinzu(spieler2);
		kontext.setAktuellenRaumZu(spieler2, raumC);
	}

	@Test
	public void testBefehlSollAusgefuehrtWerden()
	{
		assertTrue(beinS.befehlSollAusgefuehrtWerden(kontext, spieler, beinS));
		spieler.setAktiv(false);
		assertFalse(beinS.befehlSollAusgefuehrtWerden(kontext, spieler, beinS));

	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(beinS.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		kontext.entferneSpieler(spieler2);
		assertFalse(beinS.vorbedingungErfuellt(kontext, spieler, befehlszeile));
		kontext.fuegeNeuenSpielerHinzu(spieler2);
		assertTrue(beinS.vorbedingungErfuellt(kontext, spieler, kurzBs));
		kontext.entferneSpieler(spieler2);
		assertFalse(beinS.vorbedingungErfuellt(kontext, spieler, kurzBs));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(beinS.ausfuehren(kontext, spieler, befehlszeile));
		spieler.setAktiv(false);
		assertTrue(beinS.ausfuehren(kontext, spieler, befehlszeile));
		spieler.setAktiv(true);
		assertTrue(beinS.ausfuehren(kontext, spieler, kurzBs));
		spieler.setAktiv(false);
		assertTrue(beinS.ausfuehren(kontext, spieler, kurzBs));
	}

	@Test
	public void testGibFehlerAus()
	{
		beinS.gibFehlerAus(kontext, spieler, befehlszeile);
		assertEquals(TextVerwalter.BEINSTELLEN_KEINER_DA + "\n",
				kontext.getNachrichtFuer(spieler));
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.BEFEHL_BEINSTELLEN,
				beinS.getBefehlsnamen()[0]);
		assertEquals("bs", beinS.getBefehlsnamen()[1]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals("", beinS.getHilfe());
	}

}
