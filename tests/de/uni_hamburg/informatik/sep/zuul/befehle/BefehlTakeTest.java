package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public class BefehlTakeTest
{

	private BefehlTake befehlTake;

	@Before
	public void setUp() throws Exception
	{
		befehlTake = new BefehlTake();
		befehlTake.setParameter(new String[] { "tasche" });
	}

	@Test
	public void testAusfuehren()
	{
		SpielKontext kontext = new SpielKontext();
		kontext.setAktuellerRaum(new Raum("", ""));

		kontext.getAktuellerRaum().addItem(Item.UGiftkuchen);
		befehlTake.ausfuehren(kontext);

		kontext.getAktuellerRaum().addItem(Item.UKuchen);
		befehlTake.ausfuehren(kontext);
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlTake.getBefehlsname();

	}

}
