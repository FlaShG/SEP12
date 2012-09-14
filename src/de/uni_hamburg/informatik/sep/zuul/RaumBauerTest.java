package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RaumBauerTest
{

	@Test
	public void testRaumBauer()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartRaum()
	{
		String s = "south";
		String n = "north";
		String e = "east";
		String w = "west";
		
		RaumBauer bauer = new RaumBauer();
		Raum labor = bauer.getStartRaum();
		
		assertNotNull(labor);
		
		Raum westfluegel = labor.getAusgang(w);
		Raum bueroHausmeister = westfluegel.getAusgang(w);
		Raum besenkammer = bueroHausmeister.getAusgang(n);
		Raum gang  = labor.getAusgang(e);
		Raum ostfluegel = gang.getAusgang(e);
		Raum bibliothek = ostfluegel.getAusgang(e);
		Raum terasse = bibliothek.getAusgang(n);
		Raum flur = labor.getAusgang(n);
		Raum haupteingang = flur.getAusgang(e);
		Raum herrenklo = ostfluegel.getAusgang(s);
		Raum mensa = herrenklo.getAusgang(s);
		Raum vorlesung = mensa.getAusgang(w);
		Raum konferenz = vorlesung.getAusgang(e);
		Raum wohnung = labor.getAusgang(s);
		Raum innenhof = vorlesung.getAusgang(s);
		Raum chemiegebaeude = innenhof.getAusgang(s);
		Raum sekretariat = chemiegebaeude.getAusgang(w);
		Raum flurchemie = sekretariat.getAusgang(w);
		Raum bueroGegner = flurchemie.getAusgang(n);
		
		
		String[]  ausgaenge = labor.getMoeglicheAusgaenge();
		
		
		
		
		
		
		
		
		
		
		
	}

}
