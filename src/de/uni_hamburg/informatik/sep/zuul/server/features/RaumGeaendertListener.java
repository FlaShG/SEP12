package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

/**
 * Ein {@link RaumGeaendertListener} wird ausgelöst, wenn ein Spieler einen neuen Raum betritt.
 * @author felix
 *
 */
public interface RaumGeaendertListener
{
	void raumGeaendertListener(ServerKontext kontext, Spieler spieler, Raum alterRaum, Raum neuerRaum);
}
