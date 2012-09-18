package de.uni_hamburg.informatik.sep.zuul;

import de.uni_hamburg.informatik.sep.zuul.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

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
public abstract class Spiel implements ISchreiber
{
	protected Parser _parser;
	protected SpielKontext _kontext;

	/**
	 * Erzeugt ein Spiel und initialisiert die interne Raumkarte.
	 */
	public Spiel()
	{
		_parser = new Parser();
	}

	/**
	 * Schablonenmethode für Aktionen bei beendetem Spiel.
	 */
	protected void beendeSpiel()
	{
		
	}

	/**
	 * Führt das Spiel aus.
	 */
	protected void spielen()
	{
		_kontext = new SpielKontext(this);
		
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

	protected void verarbeiteEingabe(String str)
	{
		schreibeNL("> " + str);

		Befehl befehl = _parser.liefereBefehl(str);
		befehl.ausfuehren(_kontext);

		if(_kontext.isSpielZuende())
			beendeSpiel();
	}
	
	protected void restart()
	{
		spielen();
	}
}
