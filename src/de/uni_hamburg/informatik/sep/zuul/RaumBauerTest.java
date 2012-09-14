package de.uni_hamburg.informatik.sep.zuul;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Test;

public class RaumBauerTest
{

	@Test
	public void testRaumBauer()
	{
		
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
		
		
		LinkedList<String>  ausgaenge = getRichtungsListe(labor.gibMoeglicheAusgaenge());
		assertTrue(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertTrue(ausgaenge.contains(w));
		
		ausgaenge = getRichtungsListe(westfluegel.gibMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertFalse(ausgaenge.contains(s));
		assertTrue(ausgaenge.contains(w));
		
		ausgaenge = getRichtungsListe(bueroHausmeister.gibMoeglicheAusgaenge());
		assertTrue(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertFalse(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));
		
		ausgaenge = getRichtungsListe(besenkammer.gibMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertFalse(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));
		
		ausgaenge = getRichtungsListe(flur.gibMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private LinkedList<String> getRichtungsListe(String[] sArray)
	{
		LinkedList<String> sListe = new LinkedList<String>();
		for(String s : sArray)
		{
			sListe.add(s);
		}
		
		return sListe;
	}

}
