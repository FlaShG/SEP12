package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

/**
 * Ein TickListener wird alle Sekunde aufgerufen und kann so sein Status
 * abhängig von Zeit aktualisieren.
 * 
 * @author felix
 * 
 */
public interface TickListener extends Feature
{
	/**
	 * Ein TickEvent wurde ausgelöst.
	 * 
	 * @param kontext
	 */
	public void tick(ServerKontext kontext);
}
