package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

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
public abstract class Spiel
{
	protected Parser _parser;
	protected SpielKontext _kontext;

	/**
	 * Erzeugt ein Spiel und initialisiert die interne Raumkarte.
	 */
	public Spiel()
	{
		_parser = new Parser();
		_kontext = new SpielKontext(this);
	}

	protected void beendeSpiel()
	{
		_kontext.schreibeNL("Danke für dieses Spiel. Auf Wiedersehen.");
	}

	/**
	 * Führt das Spiel aus.
	 */
	protected void spielen()
	{
		zeigeWillkommenstext();
	}

	/**
	 * Gibt einen Begrüßungstext für den Spieler aus.
	 */
	protected void zeigeWillkommenstext()
	{
		_kontext.schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		_kontext.schreibeNL("");
		_kontext.zeigeRaumbeschreibung();
		_kontext.zeigeAusgaenge();
	}


	public abstract void schreibeNL(String nachricht);

	public abstract void schreibe(String nachricht);

	protected void verarbeiteEingabe(String str)
	{
		schreibeNL("> " + str);

		Befehl befehl = _parser.liefereBefehl(str);
		befehl.ausfuehren(_kontext);

		if(_kontext.isSpielZuende())
			beendeSpiel();
	}
}
