package de.uni_hamburg.informatik.sep.zuul;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.client.ClientGUI;

public class SpielGUITest
{

	private ClientGUI spielGUI;

	@Before
	public void setUp() throws Exception
	{
		spielGUI = new ClientGUI();
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
