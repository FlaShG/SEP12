package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel;

import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.RaumBauer;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.IOManager;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.ServerKontext;

public class SpielLogik {
	private static String _levelPfad;
	private ServerKontext _kontext;

	public static final int RAUMWECHSEL_ENERGIE_KOSTEN = 1;
	public static final int KUCHEN_ENERGIE_GEWINN = 3;
	public static final int GIFTKUCHEN_ENERGIE_VERLUST = 1;
	public static final int START_ENERGIE = 8;

	public void erstelleKontext() {
		Raum start = legeRaeumeAn();
		_kontext = new ServerKontext(start);

	}

	/**
	 * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
	 */
	private Raum legeRaeumeAn() {
		IOManager manager = new IOManager();
		if (_levelPfad == null) {
			manager.readLevel("./xml_dateien/testStruktur.xml");
		} else {
			manager.readLevel(_levelPfad);
		}
		// TODO: noch statisch - datei mit filechooser auswählen!!

		RaumStruktur struktur = new RaumStruktur(manager.getXmlRaeume(),
				manager.getRaeume());
		RaumBauer raumbauer = new RaumBauer(struktur);
		return raumbauer.getStartRaum();
	}

	/**
	 * Zeigt die Ausgänge des aktuellen Raumes an. Gibt diese als Liste von
	 * Räumen zurück.
	 */
	public List<Raum> zeigeAktuelleAusgaenge(Spieler spieler) {
		Raum raum = _kontext.getAktuellenRaumZu(spieler);
		return raum.getAusgaenge();
	}

	/**
	 * Gibt eine Nachricht aus und beendet das Spiel
	 * 
	 */
	public void beendeSpiel() {
		// TODO nachricht ausgeben.
		// TODO spiel beenden (Kontext?)

	}

	public boolean isRaumZielRaum(Raum raum) {
		// TODO: Ugly !!!
		return raum.getRaumart() == RaumArt.Ende;
	}

	/**
	 * Gehe mit dem Spieler in einen anderen Raum
	 * 
	 * @param spieler
	 *            der Spieler der Laufe soll
	 * @param raum
	 *            der Raum in den es gehen soll
	 */
	public void wechseleRaum(Spieler spieler, Raum raum) {
		_kontext.setAktuellenRaumZu(spieler, raum);
	}
}
