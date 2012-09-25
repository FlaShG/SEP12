package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

/**
 * Ein Panel, das eine mit JLabel beschriftete JComboBox anzeigt.
 * Es können die Elemente einer Enumeration übergeben werden.
 * Der Benutzer sucht mit diesem Element einen der Werte dieser Enumeration aus.
 * Der Index dieses Wertes wird von getWert zurück gegeben.
 * 
 * @author 0graeff
 */
public class EigenschaftEnumPanel extends EigenschaftsPanel
{
	private int _wert;
	private JComboBox<String> _enum;

	/**
	 * Erstellt ein neues {@link EigenschaftEnumPanel} mit Beschriftung, Auswahlmöglichkeiten und Startindex.
	 * @param enumValues die Auswahlmöglichkeiten, die angezeigt werden sollen.
	 * 					 Empfohlen: EINE_ENUM.values(). 
	 * @param startwert der Startindex.
	 */
	public EigenschaftEnumPanel(String beschriftung, Object[] enumValues,
			int startwert)
	{
		this(beschriftung, enumValues, startwert, null);
	}

	/**
	 * Erstellt ein neues {@link EigenschaftEnumPanel} mit Beschriftung, Auswahlmöglichkeiten, Startindex und einem Beobachter.
	 * @param enumValues die Auswahlmöglichkeiten, die angezeigt werden sollen.
	 * 					 Empfohlen: EINE_ENUM.values().
	 * @param startwert der Startindex.
	 * @param beobachter ein Beobachter, der über Änderungen in diesem Element informiert werden soll.
	 */
	public EigenschaftEnumPanel(String beschriftung, Object[] enumValues,
			int startwert, EditorBeobachter beobachter)
	{
		super(beschriftung, beobachter);
		_wert = startwert;

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

	/**
	 * Gibt den ausgewählten Index zurück.
	 * Empfehlung zur Verwendung: EINE_ENUM.values()[result]
	 */
	public int getWert()
	{
		return _wert;
	}
}
