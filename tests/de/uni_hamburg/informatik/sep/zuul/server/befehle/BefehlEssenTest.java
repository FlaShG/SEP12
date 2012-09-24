package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import org.junit.Before;
import org.junit.Test;

//TODO: Stussiger Test ist stussig
public class BefehlEssenTest
{
	private BefehlEssen _befehlEat;

	@Before
	public void setUp() throws Exception
	{
		_befehlEat = new BefehlEssen();
	}

	@Test
	public void testAusfuehren()
	{

	}

	@Test
	public void testGetBefehlsname()
	{
		_befehlEat.getBefehlsnamen();
	}

}
