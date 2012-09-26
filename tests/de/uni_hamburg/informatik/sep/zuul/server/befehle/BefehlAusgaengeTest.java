package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlAusgaengeTest
{

	BefehlAusgaenge ausgaenge = new BefehlAusgaenge();

	Raum raum = new Raum("bla", "blubb");
	Raum raumwest = new Raum("blaa", "blubb");
	Raum raumost = new Raum("blaaa", "blubb");

	ServerKontext kontext = new ServerKontext(raum);
	Spieler spieler = new Spieler("hans");
	Spieler spielerleeresInventar = new Spieler("keininventar");
	Inventar inventar = new Inventar();
	Inventar inventar2 = new Inventar();
	Befehlszeile befehlszeile = new Befehlszeile("ausgaenge");

	@Before
	public void setUp() throws Exception
	{
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raum);
		inventar.fuegeItemHinzu(Item.UKuchen);
		spieler.setInventar(inventar);
		spielerleeresInventar.setInventar(inventar2);

		raum.setAusgang(TextVerwalter.RICHTUNG_OSTEN, raumost);
		raum.setAusgang(TextVerwalter.RICHTUNG_WESTEN, raumwest);
	}

	@Test
	public void testVorbedingungErfuellt()
	{
		assertTrue(ausgaenge.vorbedingungErfuellt(kontext, spieler,
				befehlszeile));
	}

	@Test
	public void testAusfuehren()
	{
		assertTrue(ausgaenge.ausfuehren(kontext, spieler, befehlszeile));

		assertEquals("Ausgänge: ost west ", kontext.getNachrichtFuer(spieler)
				.substring(0, 19));
	}

	@Test
	public void testGibFehlerAus()
	{
	}

	@Test
	public void testGetBefehlsnamen()
	{
		assertEquals("ausgänge", ausgaenge.getBefehlsnamen()[0]);
	}

	@Test
	public void testGetHilfe()
	{
		assertEquals(TextVerwalter.HILFE_AUSGAENGE, ausgaenge.getHilfe());
	}

}
