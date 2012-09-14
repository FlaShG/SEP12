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

}
