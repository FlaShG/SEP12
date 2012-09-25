package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.SpinnerNumberModel;

/**
 * Ein Panel, das einen mit JLabel beschrifteten JSpinner anzeigt.
 * Der Wert kann niemals kleiner als 1 werden.
 * 
 * @author 0graeff
 */
public class EigenschaftIntPanelGT0 extends EigenschaftIntPanel
{
	/**
	 * Erzeugt ein neues {@link EigenschaftIntPanel} mit Startwert.
	 * 
	 * @param beschriftung der Text des davorstehenden Labels
	 * @param startwert der Wert, den die Komponente annimmt
	 */
	public EigenschaftIntPanelGT0(String beschriftung, int startwert)
	{
		this(beschriftung, startwert, null);
	}
	
	/**
	 * Erzeugt ein Eigenschaftsfeld mit einem Default-wert und Observer
	 * 
	 * @param beschriftung der Text des davorstehenden Labels
	 * @param startwert der Wert, den die Komponente annimmt
	 * @param beobachter der {@link EditorBeobachter}, der über Änderungen im Eingabefeld informiert werden soll
	 */
	public EigenschaftIntPanelGT0(String beschriftung, int startwert,
			EditorBeobachter beobachter)
	{
		super(beschriftung, startwert, beobachter);
		_zahl.setModel(new SpinnerNumberModel(startwert, 1, 99, 1));
	}
}