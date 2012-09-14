package de.uni_hamburg.informatik.sep.zuul;

import java.io.InputStream;
import java.io.PrintStream;


public class SpielKontext
{
	private static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	private static final int KUCHEN_ENERGIE_GEWINN = 3;
	
	private PrintStream _out;
	private InputStream _in;

	private Raum _aktuellerRaum;
	private boolean _spielZuende;
	private int _lebensEnergie;


	public SpielKontext(InputStream in, PrintStream out)
	{
		_in = in;
		_out = out;
		_lebensEnergie = 5;
		legeRaeumeAn();
	}

	
	public void schreibeNL(String nachricht)
	{
		_out.println(nachricht);
	}
	
	public void schreibe(String nachricht)
	{
		_out.print(nachricht);
	}
	
	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private void legeRaeumeAn()
	{
		Raum draussen, hoersaal, cafeteria, labor, buero;

		// die Räume erzeugen
		draussen = new Raum("vor dem Haupteingang der Universität");
		hoersaal = new Raum("in einem Vorlesungssaal");
		cafeteria = new Raum("in der Cafeteria der Uni");
		labor = new Raum("in einem Rechnerraum");
		buero = new Raum("im Verwaltungsbüro der Informatik");

		// die Ausgänge initialisieren
		draussen.setzeAusgang("east", hoersaal);
		draussen.setzeAusgang("south", labor);
		draussen.setzeAusgang("west", cafeteria);
		hoersaal.setzeAusgang("west", draussen);
		cafeteria.setzeAusgang("east", draussen);
		labor.setzeAusgang("north", draussen);
		labor.setzeAusgang("east", buero);
		buero.setzeAusgang("west", labor);

		_aktuellerRaum = draussen; // das Spiel startet draussen
	}


	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
	}


	public void setAktuellerRaum(Raum aktuellerRaum)
	{
		_aktuellerRaum = aktuellerRaum;
		zeigeRaumbeschreibung();
		raumBetreten();
	}


	/**
	 * @return the _spielZuende
	 */
	public boolean isSpielZuende()
	{
		return _spielZuende;
	}


	/**
	 * @param _spielZuende the _spielZuende to set
	 */
	public void beendeSpiel()
	{
		_spielZuende = true;
	}
	
	
	/**
	 * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
	 * befindet.
	 */
	public void zeigeRaumbeschreibung()
	{
		schreibeNL("Sie sind " + getAktuellerRaum().gibBeschreibung());
		schreibe("Ausgänge: ");
		
		for(String s: getAktuellerRaum().gibMoeglicheAusgaenge())
		{
			schreibe(s+" ");
		}

		schreibeNL("");
	}
	
	/**
	 * Arbeitet alle Ereignisse ab, die beim Betrete eines Raumes auftreten können, wie
	 * z.B. das Finden und Essen von Items.
	 */
	private void raumBetreten()
	{
		_lebensEnergie -= RAUMWECHSEL_ENERGIE_KOSTEN;
		schreibeNL("Das Umherwandern zieht Ihnen einen Lebensenergiepunkt ab!");
		switch(getAktuellerRaum().getItem())
		{
			case Kuchen:
				_lebensEnergie += KUCHEN_ENERGIE_GEWINN;
				schreibeNL("Sie finden ein Stück Kuchen, essen es und gewinnen dadurch drei Lebensenergie dazu!");
			break;
			case Gegengift:
				beendeSpiel();
			break;
		}
		getAktuellerRaum().removeItem();
		
		if(_lebensEnergie <= 0)
		{
			beendeSpiel();
		}
	}
}
