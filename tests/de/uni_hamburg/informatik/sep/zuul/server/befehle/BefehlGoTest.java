package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import org.junit.Before;
import org.junit.Test;

public class BefehlGoTest
{

	private BefehlGo befehlGo;

	@Before
	public void setUp() throws Exception
	{
		befehlGo = new BefehlGo();
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
