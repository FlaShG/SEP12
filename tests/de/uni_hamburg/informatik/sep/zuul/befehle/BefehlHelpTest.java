package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

public class BefehlHelpTest
{

	private BefehlHilfe befehlHelp;

	@Before
	public void setUp() throws Exception
	{
		befehlHelp = new BefehlHilfe();
	}

	@Test
	public void testAusfuehren()
	{
		befehlHelp.ausfuehren(new SpielKontext());
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlHelp.getBefehlsname();
	}

}
