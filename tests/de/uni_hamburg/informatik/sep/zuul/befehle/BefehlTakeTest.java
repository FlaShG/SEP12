package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.StubSchreiber;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlTakeTest
{

	private BefehlTake befehlTake;

	@Before
	public void setUp() throws Exception
	{
		befehlTake = new BefehlTake();
		befehlTake.setParameter(new String[] {"tasche"});
	}

	@Test
	public void testAusfuehren()
	{
		SpielKontext kontext = new SpielKontext();
		kontext.setAktuellerRaum(new Raum("",""));
		

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
