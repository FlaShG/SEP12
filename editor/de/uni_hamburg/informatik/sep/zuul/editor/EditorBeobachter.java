package de.uni_hamburg.informatik.sep.zuul.editor;

/**
 * Beobachter-Interface für Editor-Ereignisse.
 * Räume können ausgewählt (beinhaltet abwählen, erstellen und löschen),
 * verändert oder verschoben werden.
 * @author 0graeff
 *
 */
public interface EditorBeobachter
{
	public void raumwahlUpdate();

	public void eigenschaftUpdate();

	public void verschiebenUpdate();
}
