package de.uni_hamburg.informatik.sep.zuul;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * Das Ausgangssystem basiert auf einem Beispielprojekt aus dem Buch
 * "Java lernen mit BlueJ" von D. J. Barnes und M. Kölling.
 */
public class Spiel
{
	private Parser _parser;
	private static final String[] _gueltigeBefehlswoerter = new String[] {"go", "help", "quit"};
	private SpielKontext _kontext;
	

	/**
	 * Erzeugt ein Spiel und initialisiert die interne Raumkarte.
	 * @param in InputStream
	 * @param out OutputStream
	 */
	public Spiel(InputStream in, PrintStream out)
	{

		
		_parser = new Parser(in, out, new Befehlswoerter(_gueltigeBefehlswoerter));
		
		_kontext = new SpielKontext(in, out);
	}



	/**
	 * Führt das Spiel aus.
	 */
	public void spielen()
	{
		zeigeWillkommenstext();

		// Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
		// und führen sie aus, bis das Spiel beendet wird.

		boolean beendet = false;
		while(!beendet)
		{
			Befehl befehl = _parser.liefereBefehl();
			verarbeiteBefehl(befehl);
			beendet = _kontext.isSpielZuende();
		}
		_kontext.schreibeNL("Danke für dieses Spiel. Auf Wiedersehen.");
	}

	/**
	 * Gibt einen Begrüßungstext für den Spieler aus.
	 */
	private void zeigeWillkommenstext()
	{
		_kontext.schreibeNL("");
		_kontext.schreibeNL("Willkommen zu Zuul!");
		_kontext.schreibeNL("Zuul ist ein neues, unglaublich langweiliges Spiel.");
		_kontext.schreibeNL("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		_kontext.schreibeNL("");
		_kontext.zeigeRaumbeschreibung();
	}



	/**
	 * Verarbeitet einen gegebenen Befehl (führt ihn aus). Wenn der Befehl das
	 * Spiel beendet, wird 'true' zurückgeliefert, andernfalls 'false'.
	 */
	private void verarbeiteBefehl(Befehl befehl)
	{
		befehl.ausfuehren(_kontext);
//		boolean moechteBeenden = false;
//
//		if(!befehl.istBekannt())
//		{
//			_out.println("Ich weiß nicht, was Sie meinen...");
//			return false;
//		}
//
//		String befehlswort = befehl.gibBefehlswort();
//		if(befehlswort.equals("help"))
//		{
//			hilfstextAusgeben();
//		}
//		else if(befehlswort.equals("go"))
//		{
//			wechsleRaum(befehl);
//		}
//		else if(befehlswort.equals("quit"))
//		{
//			moechteBeenden = beenden(befehl);
//		}
//		return moechteBeenden;
	} 
	

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{
		Spiel spiel = new Spiel(System.in, System.out);
		spiel.spielen();
	}
}













