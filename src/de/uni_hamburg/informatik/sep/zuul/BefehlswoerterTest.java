package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Felix Wiedemann<1wiedema@informatik.uni-hamburg.de>
 *
 */
public class BefehlswoerterTest
{
	Befehlswoerter befehlswoerter;

	@Before
	public void setUp()
	{
		befehlswoerter = new Befehlswoerter(new String[] {"go", "help", "quit"});
	}

//	@Test
//	public void testBefehlswoerter()
//	{
//		fail("Not yet implemented");
//	}

	@Test
	public void testIstBefehl()
	{
		assertTrue(befehlswoerter.istBefehl("go"));
		assertTrue(befehlswoerter.istBefehl("help"));
		assertTrue(befehlswoerter.istBefehl("quit"));
		
		assertFalse(befehlswoerter.istBefehl(""));

	}

}
