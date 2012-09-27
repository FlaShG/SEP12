package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BefehlszeileTest
{
	Befehlszeile befehlszeile = new Befehlszeile("test test");
	
	String teil1 = "schwupp";
	String teil2 = "wupp";
	
	List<String> stringlist = new ArrayList<String>(); 

	@Before
	public void setUp() throws Exception
	{
		stringlist.add(teil1);
		stringlist.add(teil2);
	}

	@Test
	public void testJoin()
	{
		assertEquals("schwupp wupp", befehlszeile.join(stringlist));
		List<String> stringlist1 = new ArrayList<String>();
		assertEquals("", befehlszeile.join(stringlist1));
	}

	@Test
	public void testBeginntMit()
	{
	  assertTrue(befehlszeile.beginntMit("t"));
	}

}
