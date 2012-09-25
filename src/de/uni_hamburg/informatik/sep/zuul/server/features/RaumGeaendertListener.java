package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

/**
 * Ein {@link RaumGeaendertListener} wird ausgelöst, wenn ein Spieler einen
 * neuen Raum betritt.
 * 
 * @author felix
 * 
 */
public interface RaumGeaendertListener
{
	void raumGeaendert(ServerKontext kontext, Spieler spieler, Raum alterRaum,
			Raum neuerRaum);
}
