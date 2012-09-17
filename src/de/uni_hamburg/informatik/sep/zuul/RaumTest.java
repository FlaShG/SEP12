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
		raum = new Raum("Hauptraum");
		raumOsten = new Raum("Ostblock");
		raumWesten = new Raum("US und A");
		raumNorden = new Raum("Nordpol");
		
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
	public void testGibBeschreibung()
	{
		assertEquals("Hauptraum", raum.getBeschreibung());
	}

	@Test
	public void testgetNaechstesItem()
	{
		assertEquals(Item.Keins, raum.getNaechstesItem());
		assertEquals(Item.Keins, raumWesten.getNaechstesItem());

		raumOsten.addItem(Item.Krümel);

		assertEquals(Item.Krümel, raumOsten.getNaechstesItem());
	}
	
	@Test
	public void testSetItem()
	{
		raumOsten.addItem(Item.Krümel);

		assertEquals(Item.Krümel, raumOsten.getNaechstesItem());
		
		raumOsten.addItem(Item.Gegengift);

		assertEquals(Item.Gegengift, raumOsten.getNaechstesItem());
	}
	
	@Test
	public void testLoescheItem()
	{
		raumOsten.addItem(Item.Krümel);
		assertEquals(Item.Krümel, raumOsten.getNaechstesItem());
		
		raumOsten.loescheItem();
		assertEquals(Item.Keins, raumOsten.getNaechstesItem());
	}
	

}
