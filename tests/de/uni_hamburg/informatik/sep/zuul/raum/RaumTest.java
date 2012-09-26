package de.uni_hamburg.informatik.sep.zuul.raum;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.npcs.Maus;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * @author Felix Wiedemann<1wiedema@informatik.uni-hamburg.de>
 * 
 */
public class RaumTest
{
	Raum raum;
	Raum raumOsten;
	Raum raumWesten;
	Raum raumNorden;

	@Before
	public void setUp() throws Exception
	{
		raum = new Raum("hptr", "Hauptraum");
		raumOsten = new Raum("ostb", "Ostblock");
		raumWesten = new Raum("usa", "US und A");
		raumNorden = new Raum("npl", "Nordpol");

		raum.setAusgang("osten", raumOsten);
		raum.setAusgang("westen", raumWesten);
		raum.setAusgang("norden", raumNorden);

		raumOsten.setAusgang("raus", raum);
	}

	@Test
	public void testSetzeAusgang()
	{
		assertNull(raumWesten.getAusgang("drölf"));

		raumWesten.setAusgang("drölf", raumNorden);

		assertEquals(raumNorden, raumWesten.getAusgang("drölf"));
	}

	@Test
	public void testVerbindeZweiRaeume()
	{
		//Hat noch keinen Ausgang im osten
		assertNull(raumWesten.getAusgang("east"));

		//Hat noch keinen Ausgang im Westen
		assertNull(raumOsten.getAusgang("west"));

		raumWesten.verbindeZweiRaeume("east", raumOsten, "west");

		assertEquals(raumOsten, raumWesten.getAusgang("east"));
		assertEquals(raumWesten, raumOsten.getAusgang("west"));
	}

	@Test
	public void testGibAusgang()
	{
		assertNull(raum.getAusgang("abc"));

		assertEquals(raumOsten, raum.getAusgang("osten"));
	}

	@Test
	public void testGetMoeglicheAusgaenge()
	{
		String[] ausgaenge = { "osten", "norden", "westen" };
		assertArrayEquals(ausgaenge, raum.getMoeglicheAusgaenge());

		raum.setAusgang("gibtsNicht", raum);

		String[] ausgaenge2 = { "gibtsNicht", "osten", "norden", "westen" };
		assertArrayEquals(ausgaenge2, raum.getMoeglicheAusgaenge());
	}

	@Test
	public void testGibBeschreibung()
	{
		assertEquals("Hauptraum", raum.getBeschreibung());
	}

	@Test
	public void testgetNaechstesItem()
	{
		assertEquals(Item.Keins, raum.getNaechstesItem());
		assertEquals(Item.Keins, raumWesten.getNaechstesItem());

		raumOsten.addItem(Item.UKuchen);

		assertEquals(Item.UKuchen, raumOsten.getNaechstesItem());
	}

	@Test
	public void testSetItem()
	{
		raumOsten.addItem(Item.UKuchen);

		assertEquals(Item.UKuchen, raumOsten.getNaechstesItem());

	}

	@Test
	public void testLoescheItem()
	{
		raumOsten.addItem(Item.UKuchen);
		assertEquals(Item.UKuchen, raumOsten.getNaechstesItem());

		raumOsten.loescheItem();
		assertEquals(Item.Keins, raumOsten.getNaechstesItem());
	}

	@Test
	public void testHasMaus()
	{
		assertFalse(raum.hasMaus());
		raum.setMaus(new Maus(raum, raumOsten));
		assertTrue(raum.hasMaus());
	}

	@Test
	public void testSetMaus()
	{
		assertFalse(raumWesten.hasMaus());

		raumWesten.setMaus(new Maus(raum, raumWesten));
		assertTrue(raumWesten.hasMaus());
	}

	@Test
	public void testGetMaus()
	{
		assertFalse(raumNorden.hasMaus());
		Maus nordmaus = new Maus(raum, raumNorden);

		raumNorden.setMaus(nordmaus);

		assertEquals(nordmaus, raumNorden.getMaus());
		assertTrue(raumNorden.hasMaus());
	}

}
