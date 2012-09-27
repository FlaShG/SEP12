package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BefehlFactoryTest
{

	BefehlFactory factory = new BefehlFactory();
	
	Befehlszeile befehlszeilehilfe = new Befehlszeile("hilfe");
	Befehlszeile befehlszeilegn = new Befehlszeile("gehe nord");
	Befehlszeile befehlszeilego = new Befehlszeile("gehe ost");
	Befehlszeile befehlszeilegs = new Befehlszeile("gehe süd");
	Befehlszeile befehlszeilegw = new Befehlszeile("gehe west");
	Befehlszeile befehlszeileleer = new Befehlszeile("");
	BefehlGehe befehlgehe = new BefehlGehe();
	BefehlHilfe befehlhilfe= new BefehlHilfe();
	Befehl befehl = new BefehlGehe();


	@Before
	public void setUp() throws Exception
	{
		
	}

	@Test
	public void testGibBefehlClassOfQ()
	{
	    assertArrayEquals(befehlgehe.getBefehlsnamen(), BefehlFactory.gibBefehl(befehl.getClass()).getBefehlsnamen());
	    assertEquals(null , BefehlFactory.gibBefehl(befehlszeilegn.getClass()));
	}

	@Test
	public void testGibBefehlBefehlszeile()
	{
		 assertArrayEquals(befehlgehe.getBefehlsnamen(), BefehlFactory.gibBefehl(befehlszeilegn).getBefehlsnamen());
		    assertEquals(null , BefehlFactory.gibBefehl(befehlszeileleer));
		  assertArrayEquals(befehlhilfe.getBefehlsnamen() , BefehlFactory.gibBefehl(befehlszeilehilfe).getBefehlsnamen());
	}

	@Test
	public void testGibBefehlString()
	{
		 assertArrayEquals(befehlgehe.getBefehlsnamen(), BefehlFactory.gibBefehl("gehe").getBefehlsnamen());
	}

}
