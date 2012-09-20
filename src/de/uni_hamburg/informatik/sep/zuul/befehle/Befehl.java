package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

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
	boolean vorbedingungErfuellt(ServerKontext serverKontext, SpielerKontext kontext, Befehlszeile befehlszeile);
	
	/**
	 * Führt den Befehl aus.
	 * @require vorbedingungErfuellt(serverKontext, kontext, befehlszeile)
	 */
	boolean ausfuehren(ServerKontext serverKontext, SpielerKontext kontext, Befehlszeile befehlszeile);
	
	/**
	 * Falls der Befehl falsch verwendet wird, wird mit dieser Methode ein Fehler ausgegeben.
	 * @require !vorbedingungErfuellt(serverKontext, kontext, befehlszeile)
	 */
	void gibFehlerAus(ServerKontext serverKontext, SpielerKontext kontext, Befehlszeile befehlszeile);

	/**
	 * Gibt die (Alias-) Namen des Befehls zurück.
	 */
	String[] getBefehlsnamen();


}
