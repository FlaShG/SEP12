package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel.Spieler;

/**
 * Hält alle angemeldeten Spieler und kennt den Spielzustand. Dabei hat er
 * Wissen über die Räume und den Levelaufbau (die Raumstruktur).
 * 
 * @author 0ortmann, 0Schlund
 * 
 */
public class ServerKontext {

	private Map<Spieler, Raum> _spielerPosition;
	private Raum _startRaum;

	public ServerKontext(Raum startRaum) {
		_startRaum = startRaum;

	}

	/**
	 * Fuege einen neuen Spieler hinzu. Er startet dabei immer im Startraum.
	 * 
	 * @param spieler
	 *            der neue Spieler
	 */
	public void fuegeNeuenSpielerHinzu(Spieler spieler) {

		_spielerPosition.put(spieler, _startRaum);

	}

	/**
	 * Entferne den angegebenen Spieler aus dem Spiel.
	 * 
	 * @param spieler
	 *            zu entfernender Spieler
	 */
	public void entferneSpieler(Spieler spieler) {
		_spielerPosition.remove(spieler);
	}

	/**
	 * Gibt den Raum zurück, in dem der Spieler sich befindet.
	 * 
	 * @param spieler
	 *            der Spieler zu dem wir den Raum suchen
	 * @return der Raum in dem der Spieler ist
	 */
	public Raum getAktuellenRaumZu(Spieler spieler) {
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
	public void setAktuellenRaumZu(Spieler spieler, Raum neuerRaum) {
		assert (_spielerPosition.containsKey(spieler));
		_spielerPosition.remove(spieler);
		_spielerPosition.put(spieler, neuerRaum);
	}

	/**
	 * Gib alle registrierten Spieler in einer Liste aus.
	 * 
	 * @return Liste aller Spieler
	 */
	public List<Spieler> getSpielerListe() {
		return new ArrayList<Spieler>(_spielerPosition.keySet());
	}

	/**
	 * Zeige dem Spieler den Willkommenstext.
	 * 
	 * @param spieler
	 */
	public void zeigeWillkommensText(Spieler spieler) {
		// TODO impl!!
		// schreibeNL(TextVerwalter.EINLEITUNGSTEXT);
		// schreibeNL("");
		// zeigeRaumbeschreibung(spieler);
		// zeigeAktuelleAusgaenge(spieler);
	}

}
