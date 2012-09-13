/**
 * 
 */
package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Felix Wiedemann<1wiedema@informatik.uni-hamburg.de>
 *
 */
public class BefehlTest
{
	Befehl befehlGoEast;
	Befehl befehlNullNull;

	@Before
	public void setUp()
	{
		befehlGoEast = new  Befehl("go", "east");
		befehlNullNull = new Befehl(null,null);
	}
	
	@Test
	public void testBefehlswoerterWerdenKorrektUebernommen()
	{
		assertEquals("befehlswort is nich befehlswort", "go", befehlGoEast.gibBefehlswort());
		assertEquals("zweites wort is nich zweites wort", "east", befehlGoEast.gibZweitesWort());

		assertNull("befehlswort is nich befehlswort", befehlNullNull.gibBefehlswort());
		assertNull("zweites wort is nich zweites wort", befehlNullNull.gibZweitesWort());
	}

	@Test
	public void testBefehlHatDaten()
	{
		assertTrue(befehlGoEast.istBekannt());
		assertTrue(befehlGoEast.hatZweitesWort());
		

		assertFalse(befehlNullNull.istBekannt());
		assertFalse(befehlNullNull.hatZweitesWort());
	}

}
