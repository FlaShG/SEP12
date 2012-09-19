package de.uni_hamburg.informatik.sep.zuul.spiel;

import org.junit.Test;

public class MausTest
{

	@Test
	public void testGetRichtung()
	{
		//		RaumBauer bauer = new RaumBauer();
		Raum raum = new Raum("", "");
		Raum r2 = new Raum("", "");
		r2.addItem(Item.Gegengift);
		raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN, r2,
				TextVerwalter.RICHTUNG_SUEDEN);

		Maus maus = new Maus(raum);
		//		assertEquals(TextVerwalter.RICHTUNG_SUEDEN, maus.getRichtung());
		maus.getRichtung();

	}

}