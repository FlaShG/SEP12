package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlNehmen;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public class BefehlTakeTest
{

	private BefehlNehmen befehlTake;

	@Before
	public void setUp() throws Exception
	{
		befehlTake = new BefehlNehmen();
		befehlTake.setParameter(new String[] { "tasche" });
	}

	@Test
	public void testAusfuehren()
	{
		SpielKontext kontext = new SpielKontext();
		kontext.setAktuellerRaum(new Raum("", ""));

		kontext.getAktuellerRaum().addItem(Item.Giftkuchen);
		befehlTake.ausfuehren(kontext);

		kontext.getAktuellerRaum().addItem(Item.Kuchen);
		befehlTake.ausfuehren(kontext);
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlTake.getBefehlsname();

	}

}
