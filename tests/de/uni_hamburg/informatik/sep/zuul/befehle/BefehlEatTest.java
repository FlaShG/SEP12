package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.StubSchreiber;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlEatTest
{

	private BefehlEat befehlGo;

	@Before
	public void setUp() throws Exception
	{
		befehlGo = new BefehlEat();
		befehlGo.setParameter(new String[] {"tasche"});
	}

	@Test
	public void testAusfuehren()
	{
		befehlGo.setParameter(new String[] {"tasche"});
		befehlGo.ausfuehren(new SpielKontext(new StubSchreiber()));
		

		befehlGo.setParameter(new String[] {TextVerwalter.ORT_BODEN});
		befehlGo.ausfuehren(new SpielKontext(new StubSchreiber()));
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlGo.getBefehlsname();
		
		
	}

}
