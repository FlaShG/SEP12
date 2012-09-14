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
		
		Raum westfluegel = labor.gibAusgang(w);
		Raum bueroHausmeister = westfluegel.gibAusgang(w);
		Raum besenkammer = bueroHausmeister.gibAusgang(n);
		Raum gang  = labor.gibAusgang(e);
		Raum ostfluegel = gang.gibAusgang(e);
		Raum bibliothek = ostfluegel.gibAusgang(e);
		Raum terasse = bibliothek.gibAusgang(n);
		Raum flur = labor.gibAusgang(n);
		Raum haupteingang = flur.gibAusgang(e);
		Raum herrenklo = ostfluegel.gibAusgang(s);
		Raum mensa = herrenklo.gibAusgang(s);
		Raum vorlesung = mensa.gibAusgang(w);
		Raum konferenz = vorlesung.gibAusgang(e);
		Raum wohnung = labor.gibAusgang(s);
		Raum innenhof = vorlesung.gibAusgang(s);
		Raum chemiegebaeude = innenhof.gibAusgang(s);
		Raum sekretariat = chemiegebaeude.gibAusgang(w);
		Raum flurchemie = sekretariat.gibAusgang(w);
		Raum bueroGegner = flurchemie.gibAusgang(n);
		
		
		String[]  ausgaenge = labor.gibMoeglicheAusgaenge();
		
		
		
		
		
		
		
		
		
		
		
	}

}
