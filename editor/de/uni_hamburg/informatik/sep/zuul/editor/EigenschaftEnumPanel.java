package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

/**
 * Ein Panel, das eine mit JLabel beschriftete JComboBox anzeigt.
 * 
 * @author 0graeff
 */
public class EigenschaftEnumPanel extends EigenschaftsPanel
{
	private int _wert;
	private JComboBox<String> _enum;

	public EigenschaftEnumPanel(String beschriftung, Object[] enumValues,
			int startwert)
	{
		this(beschriftung, enumValues, startwert, null);
	}

	public EigenschaftEnumPanel(String beschriftung, Object[] enumValues,
			int startwert, EditorBeobachter beobachter)
	{
		super(beschriftung, beobachter);

		_enum = new JComboBox<String>(getStringsFromEnum(enumValues));

		_enum.setSelectedIndex(startwert);

		_enum.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				if(arg0.getStateChange() == ItemEvent.SELECTED)
				{
					_wert = _enum.getSelectedIndex();
					informiereBeobachter();
				}
			}
		});

		add(_enum);
	}

	private static String[] getStringsFromEnum(Object[] values)
	{
		String[] result = new String[values.length];
		for(int i = 0; i < values.length; ++i)
		{
			result[i] = values[i].toString();
		}
		return result;
	}

	public int getWert()
	{
		return _wert;
	}
}
