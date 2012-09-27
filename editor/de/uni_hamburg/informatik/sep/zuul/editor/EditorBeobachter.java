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
	 */
	public void raumwahlUpdate();

	/**
	 * Eine Eigenschaft eines {@link Raum}es oder des Levels wurde geändert.
	 */
	public void eigenschaftUpdate();

	/**
	 * Ein Raum wurde verschoben.
	 */
	public void verschiebenUpdate();
}
