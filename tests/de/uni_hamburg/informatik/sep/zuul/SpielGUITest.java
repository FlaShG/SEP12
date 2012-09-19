package de.uni_hamburg.informatik.sep.zuul;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpielGUITest
{

	private SpielGUI spielGUI;

	@Before
	public void setUp() throws Exception
	{
		spielGUI = new SpielGUI();
	}

	@After
	public void tearDown()
	{
		spielGUI.schliesseFenster();
	}

	@Test
	public void testBeendeSpiel()
	{
		spielGUI.beendeSpiel();
	}

}