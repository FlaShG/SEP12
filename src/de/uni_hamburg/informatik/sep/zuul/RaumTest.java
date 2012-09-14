package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		raum = new Raum("Hauptraum", Item.Kuchen);
		raumOsten = new Raum("Ostblock", Item.keins);
		raumWesten = new Raum("US und A", Item.keins);
		raumNorden = new Raum("Nordpol", Item.keins);

		raum.setzeAusgang("osten", raumOsten);
		raum.setzeAusgang("westen", raumWesten);
		raum.setzeAusgang("norden", raumNorden);

		raumOsten.setzeAusgang("raus", raum);
	}

	@Test
	public void testSetzeAusgang()
	{
		assertNull(raumWesten.gibAusgang("drölf"));

		raumWesten.setzeAusgang("drölf", raumNorden);

		assertEquals(raumNorden, raumWesten.gibAusgang("drölf"));
	}

	@Test
	public void testVerbindeZweiRaeume()
	{
		//Hat noch keinen Ausgang im osten
		assertNull(raumWesten.gibAusgang("east"));

		//Hat noch keinen Ausgang im Westen
		assertNull(raumOsten.gibAusgang("west"));

		raumWesten.verbindeZweiRaeume("east", raumOsten, "west");

		assertEquals(raumOsten, raumWesten.gibAusgang("east"));
		assertEquals(raumWesten, raumOsten.gibAusgang("west"));
	}

	@Test
	public void testGibAusgang()
	{
		assertNull(raum.gibAusgang("abc"));

		assertEquals(raumOsten, raum.gibAusgang("osten"));
	}

	@Test
	public void testGibBeschreibung()
	{
		assertEquals("Hauptraum", raum.gibBeschreibung());
	}

	@Test
	public void testGetItem()
	{
		assertEquals(Item.Kuchen, raum.getItem());
		assertEquals(Item.keins, raumWesten.getItem());
	}

}
