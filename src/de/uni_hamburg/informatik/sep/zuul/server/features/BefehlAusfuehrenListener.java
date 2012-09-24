package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

/**
 * Ein {@link BefehlAusfuehrenListener} ist ein Objekt, welches immer _vor_
 * einer Befehlsausführung aufgerufen wird.
 * 
 * @author felix
 * 
 */
public interface BefehlAusfuehrenListener
{

	/**
	 * Ein BefehlAusfuehren Event wurde ausgelöst.
	 * 
	 * @param kontext
	 *            Der aktuelle SpielKontext
	 * @return Sollen der Befehl ausgeführt werden?
	 */
	boolean befehlSollAusgefuehrtWerden(ServerKontext kontext, Spieler spieler,
			Befehl befehl);

}
