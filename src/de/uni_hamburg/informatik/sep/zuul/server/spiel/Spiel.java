package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.client.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.features.Feature;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

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
	public static final long ONE_SECOND = 1000;
	private ServerKontext _kontext;
	private Map<String, Spieler> _spielerMap;
	private Map<Spieler, String> _nachrichtenMap;
	private boolean _gestartet;

	/**
	 * Erzeuge ein neues Spiel
	 */
	public Spiel()
	{
		_kontext = SpielLogik.erstelleKontext();
		
		_spielerMap = new HashMap<String, Spieler>();
		_nachrichtenMap = new HashMap<Spieler, String>();
		//TODO in map schreiben
		setGestartet(false);
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
		SpielLogik.registriereSpieler(_kontext, neuerSpieler);
	}

	/**
	 * melde den Spieler mit dem Namen name ab.
	 * 
	 * @param name
	 *            Spielername
	 */
	public void meldeSpielerAb(String name)
	{
		SpielLogik.meldeSpielerAb(_kontext, name);
		_spielerMap.remove(name);
	}

	/**
	 * Schablonenmethode für Aktionen bei beendetem Spiel.
	 */
	public void beendeSpiel()
	{
		setGestartet(false);
	}

	/**
	 * Führt das Spiel aus.
	 */
	public void spielen()
	{
		_kontext = SpielLogik.erstelleKontext();
		SpielLogik.zeigeWillkommensText(_kontext);
		zeigeWillkommensText();
		setGestartet(true);
	}

	/**
	 * Zeige jedem angemeldeten Spieler den Willkommenstext an.
	 */
	private void zeigeWillkommensText()
	{
		_nachrichtenMap.clear(); //alte nachrichten raus (falls drin)

		for(Spieler spieler : _nachrichtenMap.keySet())
		{
			_nachrichtenMap.put(spieler, TextVerwalter.EINLEITUNGSTEXT);
		}
	}

	/**
	 * Übergib dem Spieler spieler eine nachricht als String
	 * 
	 * @param spieler
	 *            der Spieler für den die Nachricht ist
	 * @param nachricht
	 *            die Nachricht für den Spieler
	 */
	public void setNachrichtFuer(Spieler spieler, String nachricht)
	{
		_nachrichtenMap.remove(spieler); //altes entfernen
		_nachrichtenMap.put(spieler, nachricht); //neue nachricht setzen
	}

	/**
	 * Verarbeite die Eingabe eines Spielers.
	 * 
	 * @param eingabezeile
	 * @param eingabe
	 */
	public void verarbeiteEingabe(String benutzerName, String eingabe)
	{
		Spieler spieler = SpielLogik.getSpielerByName(_kontext, benutzerName);

		Befehlszeile befehlszeile = new Befehlszeile(eingabe);
		Befehl befehl = BefehlFactory.gibBefehl(befehlszeile);

		if(befehl != null)
		{
			Raum alterRaum = SpielLogik.getAktuellenRaumZu(_kontext, spieler);

			Spiel.versucheBefehlAusfuehrung(_kontext, spieler,
					befehlszeile, befehl);

			boolean result = Spiel.versucheBefehlAusfuehrung(
					_kontext, spieler, befehlszeile, befehl);

			Raum neuerRaum = SpielLogik.getAktuellenRaumZu(_kontext, spieler);

			// Wenn der Befehl erfolgreich ausgeführt wurde, rufe die Listener auf.
			if(result)
				SpielLogik.fuehreBefehlAusgefuehrtListenerAus(_kontext, spieler, befehl, alterRaum != neuerRaum);
		}
		else
			BefehlFactory.schreibeNL(_kontext, spieler,
					TextVerwalter.FALSCHEEINGABE);
	}

	/**
	 * Starte das Spiel neu.
	 * 
	 * @param level
	 */
	protected void restart(String level)
	{
		SpielLogik.beendeSpiel(_kontext);
		spielen();
	}

	/**
	 * Packe das Clienpaket für den Client mit dem Namen name.
	 * 
	 * @param name
	 * @return
	 */
	public ClientPaket packePaket(String name)
	{
		Spieler spieler = _spielerMap.get(name); //hole den Spieler mit dem namen
		String nachricht = _nachrichtenMap.get(spieler); // hole die nacricht für den spieler
		return new ClientPaket(_kontext, spieler, nachricht); //packe

	}

	public static boolean versucheBefehlAusfuehrung(ServerKontext kontext,
			Spieler spieler, Befehlszeile befehlszeile, Befehl befehl)
	{
		if(befehl.vorbedingungErfuellt(kontext, spieler, befehlszeile))
		{
			return befehl.ausfuehren(kontext, spieler, befehlszeile);
		}
		else
		{
			befehl.gibFehlerAus(kontext, spieler, befehlszeile);
			return false;
		}
	}

	void registerFeature(Feature feature)
	{

	}

	/**
	 * @return the gestartet
	 */
	public boolean isGestartet()
	{
		return _gestartet;
	}

	TimerTask _tickTimer = new TimerTask()
	{

		@Override
		public void run()
		{
			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					SpielLogik.fuehreTickListenerAus(_kontext);
				}
			});
		}
	};

	/**
	 * @param gestartet
	 *            the gestartet to set
	 */
	public void setGestartet(boolean gestartet)
	{
		if(!gestartet && _gestartet)
		{
			_tickTimer.cancel();
		}
		else if(gestartet && !_gestartet)
		{
			new Timer().schedule(_tickTimer, ONE_SECOND, ONE_SECOND);
		}
		_gestartet = gestartet;
	}
	
	public static List<Spieler> getSpielerInRaum(ServerKontext kontext, Raum raum)
	{
		ArrayList<Spieler> spielers = new ArrayList<Spieler>();
		for(Spieler spieler: kontext.getSpielerPosition().keySet())
		{
			if(SpielLogik.getAktuellenRaumZu(kontext, spieler) == raum)
				spielers.add(spieler);
		}
		return spielers;
	}

}
