package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;

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
public class Spiel {
	private SpielLogik _logik;
	private Map<String, Spieler> _spielerMap;
	private boolean _gestartet;

	/**
	 * Erzeuge ein neues Spiel
	 */
	public Spiel()
	{
		_logik = new SpielLogik();
		_spielerMap = new HashMap<String, Spieler>();
		_gestartet = false;
	}

	/**
	 * Erzeuge einen neuen Spieler aus dem übergebenen Namen. Sein Inventar ist
	 * leer und er bekommt die Standardlebensenergie. Siehe auch
	 * {@link SpielLogik}.
	 * 
	 * @param name
	 *            Name des Spielers
	 */
	public void meldeSpielerAn(String name)
	{
		Spieler neuerSpieler = new Spieler(name, SpielLogik.START_ENERGIE,
				new Inventar());
		_spielerMap.put(name, neuerSpieler);
		_logik.registriereSpieler(neuerSpieler);
	}

	/**
	 * melde den Spieler mit dem Namen name ab.
	 * 
	 * @param name
	 *            Spielername
	 */
	public void meldeSpielerAb(String name)
	{
		_logik.meldeSpielerAb(name);
		_spielerMap.remove(name);
	}

	public boolean istGestartet()
	{
		return _gestartet;
	}

	/**
	 * Schablonenmethode für Aktionen bei beendetem Spiel.
	 */
	public void beendeSpiel()
	{
		_gestartet = false;
	}

	/**
	 * Führt das Spiel aus.
	 */
	public void spielen()
	{
		_logik.erstelleKontext();
		_logik.zeigeWillkommensText();
		_gestartet = true;
	}

	/**
	 * Verarbeite die Eingabe eines Spielers.
	 * 
	 * @param eingabezeile
	 * @param spieler
	 */
	protected void verarbeiteEingabe(String eingabezeile, Spieler spieler)
	{

		Befehl befehl = parseEingabezeile(eingabezeile);
		_logik.fuehreBefehlAus(befehl, spieler);
	}

	/**
	 * Starte das Spiel neu.
	 * 
	 * @param level
	 */
	protected void restart(String level)
	{
		_logik.beendeSpiel();
		spielen();
	}

	/**
	 * @param eingabezeile
	 * @return geparster Befehl
	 */
	public Befehl parseEingabezeile(String eingabezeile)
	{
		String[] input = eingabezeile.split(" +");

		String[] parameter = new String[0];
		String befehl = "";

		if(input.length > 0)
		{
			befehl = input[0];
			if(input.length > 1)
				parameter = Arrays.copyOfRange(input, 1, input.length);

		}

		// TODO: new befehl structure
		return BefehlFactory.get(befehl, parameter);
	}

	/**
	 * Packe das Clienpaket für den Client mit dem Namen name.
	 * 
	 * @param name
	 * @return
	 */
	public ClientPaket packePaket(String name)
	{
		Spieler spieler = _spielerMap.get(name);
		return new ClientPaket(_logik.getKontex(), spieler);

	}

	// /**
	// * Privates Klassenattribut, wird beim erstmaligen Gebrauch (nicht beim
	// * Laden) der Klasse erzeugt
	// */
	// private static Spiel instance;
	//
	// /**
	// * Statische Methode „getInstance()“ liefert die einzige Instanz der
	// Klasse
	// * zurück. Ist synchronisiert und somit thread-sicher.
	// */
	// public synchronized static Spiel getInstance() {
	// if (instance == null) {
	// if (!Programm.isOnconsole()) {
	// instance = new SpielGUI();
	// } else {
	// instance = new SpielConsole();
	// }
	// }
	// return instance;
	// }
}
