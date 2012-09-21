package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.HashMap;
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
		_logik.erstelleKontext();
		_logik.zeigeWillkommensText();
		setGestartet(true);
	}

	/**
	 * Verarbeite die Eingabe eines Spielers.
	 * 
	 * @param eingabezeile
	 * @param eingabe
	 */
	public void verarbeiteEingabe(String benutzerName, String eingabe)
	{
		Spieler spieler = _logik.getKontext().getSpielerByName(benutzerName);

		Befehlszeile befehlszeile = new Befehlszeile(eingabe);
		Befehl befehl = BefehlFactory.gibBefehl(befehlszeile);

		if(befehl != null)
		{
			Spiel.versucheBefehlAusfuehrung(_logik.getKontext(), spieler,
					befehlszeile, befehl);

			boolean result = Spiel.versucheBefehlAusfuehrung(
					_logik.getKontext(), spieler, befehlszeile, befehl);

			// Wenn der Befehl erfolgreich ausgeführt wurde, rufe die Listener auf.
			if(result)
				_logik.fuehreBefehlAusgefuehrtListenerAus(spieler, befehl);
		}
		else
			BefehlFactory.schreibeNL(_logik.getKontext(), spieler,
					TextVerwalter.FALSCHEEINGABE);
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
	 * Packe das Clienpaket für den Client mit dem Namen name.
	 * 
	 * @param name
	 * @return
	 */
	public ClientPaket packePaket(String name) throws RemoteException
	{
		Spieler spieler = _spielerMap.get(name);
		return new ClientPaket(_logik.getKontext(), spieler);

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
			try
			{
				SwingUtilities.invokeAndWait(new Runnable()
				{

					@Override
					public void run()
					{
						_logik.fuehreTickListenerAus();
					}
				});
			}
			catch(InvocationTargetException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
}
