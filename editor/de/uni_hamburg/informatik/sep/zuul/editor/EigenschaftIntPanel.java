package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Ein Panel, das einen mit JLabel beschrifteten JSpinner anzeigt.
 * 
 * @author 0graeff
 */
public class EigenschaftIntPanel extends EigenschaftsPanel
{
	private JSpinner _zahl;

	/**
	 * Erzeugt ein neues {@link EigenschaftIntPanel} mit Startwert.
	 * 
	 * @param beschriftung der Text des davorstehenden Labels
	 * @param startwert der Wert, den die Komponente annimmt
	 */
	public EigenschaftIntPanel(String beschriftung, int startwert)
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
	public EigenschaftIntPanel(String beschriftung, int startwert,
			EditorBeobachter beobachter)
	{
		super(beschriftung, beobachter);

		_zahl = new JSpinner();
		_zahl.setModel(new SpinnerNumberModel(startwert, 0, 99, 1));
		_zahl.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				informiereBeobachter();
			}
		});
		Dimension dim = new Dimension(80, 18);
		_zahl.setSize(dim);
		_zahl.setPreferredSize(dim);
		_zahl.setMinimumSize(dim);

		add(_zahl);
	}

	/**
	 * Gibt den aktuellen Zahlenwert zurück, der in der Komponente eingestellt
	 * ist.
	 */
	public int getWert()
	{
		return (Integer)_zahl.getValue();
	}

	/**
	 * Setzt den aktuellen Wert.
	 */
	public void setWert(int wert)
	{
		_zahl.setValue(wert);
	}
}
