package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

//NOCH NICHT GETESTET
/**
 * Ein Panel, das eine mit JLabel beschriftete JCheckBox anzeigt.
 * 
 * @author 0graeff
 */
public class EigenschaftBooleanPanel extends EigenschaftsPanel
{
	private boolean _wert;

	/**
	 * Erstellt ein neues {@link EigenschaftBooleanPanel} mit Beschriftung und Startwert.
	 */
	public EigenschaftBooleanPanel(String beschriftung, boolean startwert)
	{
		this(beschriftung, startwert, null);
	}

	/**
	 * Erstellt ein neues {@link EigenschaftBooleanPanel} mit Beschriftung, Startwert und Beobachter.
	 * @param beobachter {@link EditorBeobachter}, der über Änderungen dieses Elements informiert werden soll.
	 */
	public EigenschaftBooleanPanel(String beschriftung, boolean startwert,
			EditorBeobachter beobachter)
	{
		super(beschriftung, beobachter);
		_wert = startwert;

		JCheckBox checkbox = new JCheckBox();
		checkbox.setSelected(startwert);
		checkbox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				_wert = arg0.getStateChange() == ItemEvent.SELECTED;
				informiereBeobachter();
			}
		});

		add(checkbox);
	}

	/**
	 * Gibt den in diesem Element eingestellten Wert zurück.
	 */
	public boolean getWert()
	{
		return _wert;
	}
}
