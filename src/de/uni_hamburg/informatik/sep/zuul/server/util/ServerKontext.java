package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Hält alle angemeldeten Spieler und kennt den Spielzustand. Dabei hat er
 * Wissen über die Räume und den Levelaufbau (die Raumstruktur).
 * 
 * @author 0ortmann, 0Schlund
 * 
 */
public class ServerKontext
{

	private Map<ServerKontext, Raum> _spielerPosition;
	private Raum _startRaum;

	public ServerKontext(Raum startRaum)
	{
		_startRaum = startRaum;

	}

	/**
	 * Fuege einen neuen Spieler hinzu. Er startet dabei immer im Startraum.
	 * 
	 * @param spieler
	 *            der neue Spieler
	 */
	public void fuegeNeuenSpielerHinzu(ServerKontext spieler)
	{

		_spielerPosition.put(spieler, _startRaum);

	}

	/**
	 * Gibt den Raum zurück, in dem der Spieler sich befindet.
	 * 
	 * @param spieler
	 *            der Spieler zu dem wir den Raum suchen
	 * @return der Raum in dem der Spieler ist
	 */
	public Raum getAktuellenRaumZu(ServerKontext spieler)
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
	public void setAktuellenRaumZu(ServerKontext spieler, Raum neuerRaum)
	{
		assert (_spielerPosition.containsKey(spieler));
		_spielerPosition.remove(spieler);
		_spielerPosition.put(spieler, neuerRaum);
	}

	public Collection<ServerKontext> getSpieler()
	{
		return new ArrayList<ServerKontext>(_spielerPosition.keySet());
	}

	public List<Raum> getRaeumeInDemSichSpielerAufhalten()
	{
		return new ArrayList<Raum>(_spielerPosition.values());
	}

}
