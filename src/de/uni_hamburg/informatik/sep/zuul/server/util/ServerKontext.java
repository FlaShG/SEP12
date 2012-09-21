package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

/**
 * Hält alle angemeldeten Spieler und kennt den Spielzustand. Dabei hat er
 * Wissen über die Räume und den Levelaufbau (die Raumstruktur).
 * 
 * @author 0ortmann, 0Schlund
 * 
 */
public class ServerKontext
{

	private Map<Spieler, Raum> _spielerPosition;
	private Raum _startRaum;

	public ServerKontext(Raum startRaum)
	{

		_startRaum = startRaum;
		_spielerPosition = new HashMap<Spieler, Raum>();
	}

	/**
	 * Fuege einen neuen Spieler hinzu. Er startet dabei immer im Startraum.
	 * 
	 * @param spieler
	 *            der neue Spieler
	 */
	public void fuegeNeuenSpielerHinzu(Spieler spieler)
	{

		_spielerPosition.put(spieler, _startRaum);

	}

	/**
	 * Entferne den angegebenen Spieler aus dem Spiel.
	 * 
	 * @param name
	 *            name des zu entfernenden Spielers
	 */
	public void entferneSpieler(String name)
	{
		for(Spieler spieler : getSpielerListe())
		{
			if(name.equals(spieler.getName()))
			{
				_spielerPosition.remove(spieler);
			}
		}
	}

	/**
	 * Gibt den Raum zurück, in dem der Spieler sich befindet.
	 * 
	 * @param spieler
	 *            der Spieler zu dem wir den Raum suchen
	 * @return der Raum in dem der Spieler ist
	 */
	public Raum getAktuellenRaumZu(Spieler spieler)
	{
		return _spielerPosition.get(spieler);
	}

	/**
	 * Setze den aktuellen Raum des Spielers neu.
	 * 
	 * @param spieler
	 *            der Spieler
	 * @param neuerRaum
	 *            der neu aktuelle aufenthalts Raum des Spielers
	 */
	public void setAktuellenRaumZu(Spieler spieler, Raum neuerRaum)
	{
		assert (_spielerPosition.containsKey(spieler));
		_spielerPosition.remove(spieler);
		_spielerPosition.put(spieler, neuerRaum);
	}

	/**
	 * Gib alle registrierten Spieler in einer Liste aus.
	 * 
	 * @return Liste aller Spieler
	 */
	public List<Spieler> getSpielerListe()
	{
		return new ArrayList<Spieler>(_spielerPosition.keySet());
	}

	/**
	 * Gibt eine Liste von Spielern aus dem aktuellen Raum.
	 * 
	 * @param aktuellerRaum
	 *            der Raum
	 * @return alle Spieler in dem Raum
	 */
	public List<String> getSpielerNamenInRaum(Raum aktuellerRaum)
	{
		ArrayList<String> result = new ArrayList<String>();
		assert _spielerPosition.containsValue(aktuellerRaum);

		for(Spieler s : _spielerPosition.keySet())
		{
			Raum raum = _spielerPosition.get(s);
			if(raum.equals(aktuellerRaum))
			{
				result.add(s.getName());
			}
		}
		return result;

	}

	/**
	 * Zeige dem Spieler den Willkommenstext.
	 * 
	 * @param spieler
	 */
	public void zeigeWillkommensText(Spieler spieler)
	{
		// TODO impl!!
		// schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		// schreibeNL("");
		// zeigeRaumbeschreibung(spieler);
		// zeigeAktuelleAusgaenge(spieler);
	}

	/**
	 * Gibt die Nachricht für den Spieler Spieler.
	 * 
	 * @param spieler
	 * @return
	 */
	public String getNachrichtFuer(Spieler spieler)
	{
		return "";
		// TODO impl !!!!
	}

	public Spieler getSpielerByName(String benutzerName)
	{
		for(Spieler s : _spielerPosition.keySet())
		{
			if(benutzerName.equals(s.getName()))
			{
				return s;
			}
		}
		return null;
	}

	public List<Raum> getRaeumeInDemSichSpielerAufhalten()
	{
		return new ArrayList<Raum>(_spielerPosition.values());
	}

	public List<Spieler> getSpielerInRaum(Raum raum)
	{
		ArrayList<Spieler> spielers = new ArrayList<Spieler>();
		for(Spieler spieler: _spielerPosition.keySet())
		{
			if(getAktuellenRaumZu(spieler) == raum)
				spielers.add(spieler);
		}
		return spielers;
	}

}
