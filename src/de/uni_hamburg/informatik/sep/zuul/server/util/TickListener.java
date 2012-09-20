package de.uni_hamburg.informatik.sep.zuul.server.util;

/**
 * Ein TickListener ist ein Objekt, welches alle Sekunde ausgeführt wird.
 * 
 * @author felix
 * 
 */
public interface TickListener
{

	/**
	 * Ein Tick Event wurde ausgelöst.
	 * 
	 * @param kontext
	 *            Der aktuelle SpielKontext
	 * @param hasRoomChanged
	 *            Ob der Raum mit dem letzten Befehl verändert wurde.
	 * @return Sollen noch weitere TickListener ausgeführt werden?
	 */
	void tick(ServerKontext kontext);

}
