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

	public EigenschaftBooleanPanel(String beschriftung, boolean startwert)
	{
		this(beschriftung, startwert, null);
	}

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

	public boolean getWert()
	{
		return _wert;
	}
}
