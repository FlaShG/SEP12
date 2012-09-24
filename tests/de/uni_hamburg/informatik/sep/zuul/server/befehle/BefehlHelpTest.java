package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import org.junit.Before;
import org.junit.Test;

public class BefehlHelpTest
{

	private BefehlHelp befehlHelp;

	@Before
	public void setUp() throws Exception
	{
		befehlHelp = new BefehlHelp();
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
