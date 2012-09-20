package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlGehe;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public class BefehlGoTest
{

	private BefehlGehe befehlGo;

	@Before
	public void setUp() throws Exception
	{
		befehlGo = new BefehlGehe();
		befehlGo.setParameter(new String[] { "osten" });
	}

	@Test
	public void testAusfuehren()
	{
		befehlGo.ausfuehren(new SpielKontext());
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlGo.getBefehlsname();

	}

}
