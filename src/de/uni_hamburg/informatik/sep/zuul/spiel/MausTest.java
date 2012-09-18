package de.uni_hamburg.informatik.sep.zuul.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MausTest
{

	@Test
	public void testGetRichtung()
	{
		RaumBauer bauer = new RaumBauer();
		
		Maus maus = new Maus(bauer.getStartRaum());
		assertEquals(TextVerwalter.RICHTUNG_SUEDEN, maus.getRichtung());

	}

}
