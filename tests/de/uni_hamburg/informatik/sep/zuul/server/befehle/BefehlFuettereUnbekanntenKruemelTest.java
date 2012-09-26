package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettereUnbekanntenKruemelTest
{
	BefehlFuettereUnbekanntenKruemel futterUK = new BefehlFuettereUnbekanntenKruemel();
	Raum raumC = new Raum("Center", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Befehlszeile nurfutter = new Befehlszeile("fütter");
	Inventar inventar = new Inventar();
	Katze katze;

	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.UKuchen);
		spieler.setInventar(inventar);
		katze = new Katze(raumC);
		raumC.setKatze(katze);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(futterUK.vorbedingungErfuellt(kontext, spieler, nurfutter));
		futterUK.ausfuehren(kontext, spieler, nurfutter);
		assertFalse(futterUK.vorbedingungErfuellt(kontext, spieler, nurfutter));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(spieler.getInventar().isGefuellt());
		assertTrue(futterUK.ausfuehren(kontext, spieler, nurfutter));
		assertFalse(spieler.getInventar().isGefuellt());
	}

	@Test
	public void testGibFehlerAus()
	{
		futterUK.gibFehlerAus(kontext, spieler, nurfutter);
		assertTrue(spieler.getInventar().hasAnyKuchen());
		raumC.setKatze(null);
		futterUK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.BEFEHL_FUETTERE_NICHTS_DA_ZUM_FUETTERN
				+ "\n", kontext.getNachrichtFuer(spieler));
		assertTrue(spieler.getInventar().hasAnyKuchen());
		futterUK.ausfuehren(kontext, spieler, nurfutter);
		futterUK.gibFehlerAus(kontext, spieler, nurfutter);
		assertEquals(TextVerwalter.MAUS_KEIN_KRUEMEL + "\n",
				kontext.getNachrichtFuer(spieler));

	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals(TextVerwalter.HILFE_FEED, futterUK.getHilfe());
	}

}
