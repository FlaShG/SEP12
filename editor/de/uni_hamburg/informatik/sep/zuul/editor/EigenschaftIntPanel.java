package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Ein Panel, das einen mit JLabel beschrifteten JSpinner anzeigt.
 * @author 0graeff
 */
public class EigenschaftIntPanel extends EigenschaftsPanel
{
	private JSpinner _zahl;
	private int _wert = 0;
	
	/**
	 * Erzeugt ein Eigenschaftsfeld mit Startwert
	 * @param beschriftung Der Text des davorstehenden Labels
	 * @param startwert Der Wert, den die Komponente annimmt
	 */
	public EigenschaftIntPanel(String beschriftung, int startwert)
	{
		this(beschriftung, startwert, null);
	}

	/**
	 * Erzeugt ein Eigenschaftsfeld mit einem Default-wert und Observer
	 * @param beschriftung Der Text des davorstehenden Labels
	 * @param startwert Der Wert, den die Komponente annimmt
	 * @param observer Der Observer, der über Änderungen im Eingabefeld informiert werden soll
	 */
	public EigenschaftIntPanel(String beschriftung, int startwert, Observer beobachter)
	{
		super(beschriftung, beobachter);
		_wert = startwert;
		
		_zahl = new JSpinner();
		_zahl.setValue(startwert);
		_zahl.addChangeListener(new ChangeListener()
		{
			
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				_wert = (Integer)_zahl.getValue();
				informiereBeobachter();
			}
		});
		Dimension dim = new Dimension(80,18);
		_zahl.setSize(dim);
		_zahl.setPreferredSize(dim);
		_zahl.setMinimumSize(dim);
		
		add(_zahl);
	}
	
	/**
	 * Gibt den aktuellen Zahlenwert zurück, der in der Komponente eingestellt ist.
	 */
	public int getWert()
	{
		return _wert;
	}
}
