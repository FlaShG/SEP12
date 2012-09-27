package de.uni_hamburg.informatik.sep.zuul.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class ClientPaketTest
{
	ClientPaket cp;
	Raum raumC = new Raum("Center", "blubb");
	Raum raumN = new Raum("Nord", "blubb");
	ServerKontext kontext = new ServerKontext(raumC);
	Spieler spieler = new Spieler("hans");
	Inventar inventar = new Inventar();
	private Map<String, Boolean> verfuegbareBefehle;
	Katze katze;
	Maus maus;

	@Before
	public void setUp() throws Exception
	{
		inventar.fuegeItemHinzu(Item.IKuchen);
		spieler.setInventar(inventar);
		spieler.setLebensEnergie(10);
		raumC.setAusgang(TextVerwalter.RICHTUNG_NORDEN, raumN);
		raumN.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, raumC);
		katze = new Katze(raumN);
		//		raumN.setKatze(katze);
		maus = new Maus(raumC, raumN);
		//		raumC.setMaus(maus);
		kontext.fuegeNeuenSpielerHinzu(spieler);
		kontext.setAktuellenRaumZu(spieler, raumC);
		verfuegbareBefehle = new HashMap<String, Boolean>();
		for(Entry<String, Befehl> entry : BefehlFactory.getMap().entrySet())
		{
			String befehlsname = entry.getKey();
			Befehl befehl = entry.getValue();
			boolean befehlVerfuegbar = false ? false : befehl
					.vorbedingungErfuellt(kontext, spieler, new Befehlszeile(
							befehlsname));
			verfuegbareBefehle.put(befehlsname, befehlVerfuegbar);
		}
		cp = new ClientPaket(kontext, spieler, "Ich CP");
	}

	@Test
	public void testHasKatze()
	{
		assertFalse(cp.hasKatze());
		raumC.setKatze(katze);
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertTrue(cp.hasKatze());
	}

	@Test
	public void testHasMaus()
	{
		assertFalse(cp.hasMaus());
		raumC.setMaus(maus);
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertTrue(cp.hasMaus());
	}

	@Test
	public void testGetItems()
	{
		//TODO ClientPaket testGetItems()
	}

	@Test
	public void testGetNachricht()
	{
		assertEquals("Ich CP", cp.getNachricht());
	}

	@Test
	public void testGetLebensEnergie()
	{
		assertEquals(10, cp.getLebensEnergie());
	}

	@Test
	public void testGetAndereSpieler()
	{
		assertEquals("hans", cp.getAndereSpieler().get(0));
	}

	@Test
	public void testGetRaumArt()
	{
		assertEquals(RaumArt.Normal, cp.getRaumArt());
		raumC.setRaumart(RaumArt.Start);
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertEquals(RaumArt.Start, cp.getRaumArt());
		raumC.setRaumart(RaumArt.Ende);
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertEquals(RaumArt.Ende, cp.getRaumArt());
	}

	@Test
	public void testGetSpielerName()
	{
		assertEquals("hans", cp.getSpielerName());
	}

	@Test
	public void testGetMoeglicheAusgaenge()
	{
		assertEquals("nord", cp.getMoeglicheAusgaenge().get(0));
	}

	@Test
	public void testGetRaumID()
	{
		assertEquals(raumC.getId(), cp.getRaumID());
	}

	@Test
	public void testGetVerfuegbareBefehle()
	{
		assertEquals(verfuegbareBefehle, cp.getVerfuegbareBefehle());
	}

	@Test
	public void testIsShowLoseScreen()
	{
		assertFalse(cp.isShowLoseScreen());
		spieler.setLebensEnergie(0);
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertTrue(cp.isShowLoseScreen());
	}

	@Test
	public void testIsShowWinScreen()
	{
		assertFalse(cp.isShowWinScreen());
		assertFalse(spieler.hatGewonnen());
		spieler.gewinnt();
		spieler.setLebensEnergie(0);
		spieler.hatGewonnen();
		assertTrue(spieler.hatGewonnen());
		cp = new ClientPaket(kontext, spieler, "Ich CP");
		assertTrue(cp.isShowWinScreen());
	}

}
