package de.uni_hamburg.informatik.sep.zuul.befehle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.StubSchreiber;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

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
		befehlHelp.ausfuehren(new SpielKontext(new StubSchreiber()));
	}

	@Test
	public void testGetBefehlsname()
	{
		befehlHelp.getBefehlsname();
	}

}
