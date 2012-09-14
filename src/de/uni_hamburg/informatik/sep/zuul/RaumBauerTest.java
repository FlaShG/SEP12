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

		Raum westfluegel = labor.getAusgang(w);
		Raum bueroHausmeister = westfluegel.getAusgang(w);
		Raum besenkammer = bueroHausmeister.getAusgang(n);
		Raum gang = labor.getAusgang(e);
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

		LinkedList<String> ausgaenge = getRichtungsListe(labor
				.getMoeglicheAusgaenge());
		assertTrue(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertTrue(ausgaenge.contains(w));

		ausgaenge = getRichtungsListe(westfluegel.getMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertFalse(ausgaenge.contains(s));
		assertTrue(ausgaenge.contains(w));

		ausgaenge = getRichtungsListe(bueroHausmeister.getMoeglicheAusgaenge());
		assertTrue(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertFalse(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));

		ausgaenge = getRichtungsListe(besenkammer.getMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertFalse(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));

		ausgaenge = getRichtungsListe(flur.getMoeglicheAusgaenge());
		assertFalse(ausgaenge.contains(n));
		assertTrue(ausgaenge.contains(e));
		assertTrue(ausgaenge.contains(s));
		assertFalse(ausgaenge.contains(w));

		//		String[]  ausgaenge = labor.getMoeglicheAusgaenge();

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
