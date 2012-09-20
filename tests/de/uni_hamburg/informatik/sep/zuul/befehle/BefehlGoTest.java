package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

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
