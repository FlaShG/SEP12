package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlEssen;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlEatTest
{

	private BefehlEssen befehlEat;

	@Before
	public void setUp() throws Exception
	{
		befehlEat = new BefehlEssen();
		befehlEat.setParameter(new String[] { "tasche" });
	}

	@Test
	public void testAusfuehren()
	{
		befehlEat.setParameter(new String[] { "tasche" });
		befehlEat.ausfuehren(new SpielKontext());

		befehlEat.setParameter(new String[] { TextVerwalter.ORT_BODEN });
		befehlEat.ausfuehren(new SpielKontext());
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlEat.getBefehlsname();

	}

}
