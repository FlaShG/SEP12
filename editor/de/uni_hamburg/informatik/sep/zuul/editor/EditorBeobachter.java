package de.uni_hamburg.informatik.sep.zuul.editor;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Beobachter-Interface für Editor-Ereignisse. Räume können ausgewählt
 * (beinhaltet abwählen, erstellen und löschen), verändert oder verschoben
 * werden.
 * 
 * @author 0graeff
 * 
 */
public interface EditorBeobachter
{
	/**
	 * Ein {@link Raum} wurde ausgewählt.
	 * @param neuerRaum ob der Raum brandneu ist.
	 */
	public void raumwahlUpdate(boolean neuerRaum);

	/**
	 * Eine Eigenschaft eines {@link Raum}es oder des Levels wurde geändert.
	 */
	public void eigenschaftUpdate();

	/**
	 * Ein Raum wurde verschoben.
	 */
	public void verschiebenUpdate();
}
