package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;

/**
 * Ein {@link BefehlAusgefuehrtListener} ist ein Objekt, welches immer nach
 * einer _erfolgreichen_ Befehlsausführung aufgerufen wird.
 * 
 * @author felix
 * 
 */
public interface BefehlAusgefuehrtListener
{

	/**
	 * Ein BefehlAusgefuehrt Event wurde ausgelöst.
	 * 
	 * @param kontext
	 *            Der aktuelle SpielKontext
	 * @param hasRoomChanged
	 *            Ob der Raum mit dem letzten Befehl verändert wurde.
	 * @return Sollen noch weitere {@link BefehlAusgefuehrtListener} ausgeführt
	 *         werden?
	 */
	boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler,
			boolean hasRoomChanged);

}
