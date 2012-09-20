package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

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
		assertEquals(TextVerwalter.RICHTUNG_NORDEN,
				BefehlFeed.bestimmeRichtung(Item.UKuchen,
						TextVerwalter.RICHTUNG_NORDEN,
						new String[] { TextVerwalter.RICHTUNG_SUEDEN }));

		String falscheRichtung = BefehlFeed.bestimmeRichtung(Item.UGiftkuchen,
				TextVerwalter.RICHTUNG_NORDEN,
				new String[] { TextVerwalter.RICHTUNG_SUEDEN });

		assertFalse(falscheRichtung == TextVerwalter.RICHTUNG_NORDEN);
		assertTrue(falscheRichtung == TextVerwalter.RICHTUNG_SUEDEN);

	}

}
