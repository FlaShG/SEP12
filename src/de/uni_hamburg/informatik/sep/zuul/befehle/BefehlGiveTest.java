package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.Item;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public class BefehlGiveTest
{

	private BefehlGive _befehlGive;

	@Before
	public void setUp() throws Exception
	{
		_befehlGive = new BefehlGive();
	}

	@Test
	public void testBestimmeRichtung()
	{
		assertEquals(TextVerwalter.RICHTUNG_NORDEN, BefehlGive.bestimmeRichtung(
				Item.Kuchen, TextVerwalter.RICHTUNG_NORDEN));

		String falscheRichtung = BefehlGive.bestimmeRichtung(Item.Giftkuchen,
				TextVerwalter.RICHTUNG_NORDEN);
		assertFalse(falscheRichtung == TextVerwalter.RICHTUNG_NORDEN);
	}

}
