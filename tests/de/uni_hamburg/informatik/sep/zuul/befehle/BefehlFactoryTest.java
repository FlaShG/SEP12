package de.uni_hamburg.informatik.sep.zuul.befehle;

import org.junit.Before;
import org.junit.Test;

public class BefehlFactoryTest
{

	@Before
	public void setUp() throws Exception
	{
		BefehlFactory._map.isEmpty();
	}

	@Test
	public void testGetBefehl()
	{
		BefehlFactory.get("gehe", null);

	}
}