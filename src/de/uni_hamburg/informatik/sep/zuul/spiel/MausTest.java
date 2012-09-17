package de.uni_hamburg.informatik.sep.zuul.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MausTest
{

	@Test
	public void testGetRichtung()
	{
		Maus maus = new Maus(TextVerwalter.RICHTUNG_NORDEN);
		assertEquals(TextVerwalter.RICHTUNG_NORDEN, maus.getRichtung());

	}

}
