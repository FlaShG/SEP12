package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

/**
 * Informationen über einen vom Benutzer eingegebenen Befehl. Ein Befehl besteht
 * aus zwei Zeichenketten: einem Befehlswort und einem zweiten Wort. Beim Befehl
 * "go west" beispielsweise sind die beiden Zeichenketten "go" und "west". Wenn
 * der Befehl nur aus einem Wort bestand, dann ist das zweite Wort
 * <code>null</code>.
 * 
 * Benutzer dieser Klasse sind dafür zuständig, Befehle auf ihre Gültigkeit zu
 * prüfen. Wenn ein Spieler einen ungültigen Befehl eingegeben hat, sollte das
 * Befehlswort auf <code>null</code> gesetzt werden.
 */
public interface Befehl
{
	/**
	 * Überprüft, ob die Vorbedingunen erfüllt sind.
	 */
	boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile);

	/**
	 * Führt den Befehl aus.
	 * 
	 * @require vorbedingungErfuellt(serverKontext, kontext, befehlszeile)
	 */
	boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile);

	/**
	 * Falls der Befehl falsch verwendet wird, wird mit dieser Methode ein
	 * Fehler ausgegeben.
	 * 
	 * @require !vorbedingungErfuellt(serverKontext, kontext, befehlszeile)
	 */
	void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile);

	/**
	 * Gibt die (Alias-) Namen des Befehls zurück.
	 */
	String[] getBefehlsnamen();
	
	/**
	 * Gibt einen spezifischen Hilfetext zu einem Befehl zurück.
	 */
	String getHilfe();

}
