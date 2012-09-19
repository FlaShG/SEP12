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

public class Eigenschaftsfeld extends JPanel
{
	public static final int ZAHL = 0;
	public static final int BOOLEAN = 1;
	private static final int ENUM = 2;

	
	private Observer _observer;
	private JCheckBox _checkbox;
	private JSpinner _zahl;
	private JComboBox<String> _enum; 
	private int _value = 0;
	
	public Eigenschaftsfeld(String beschriftung, int typ, int startwert)
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
		
		_value = startwert;
		
		add(new JLabel(beschriftung));
		
		switch(typ)
		{
			case ZAHL:
				_zahl = new JSpinner();
				_zahl.setValue(startwert);
				_zahl.addChangeListener(new ChangeListener()
				{
					
					@Override
					public void stateChanged(ChangeEvent arg0)
					{
						_value = (Integer)_zahl.getValue();
						informiereBeobachter();
					}
				});
				Dimension dim = new Dimension(80,18);
				_zahl.setSize(dim);
				_zahl.setPreferredSize(dim);
				_zahl.setMinimumSize(dim);
				
				add(_zahl);
				
			break;
			
			case BOOLEAN:
				_checkbox = new JCheckBox();
				_checkbox.setSelected(startwert == 1);
				_checkbox.addItemListener(new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent arg0)
					{
						_value = arg0.getStateChange() == ItemEvent.SELECTED ? 1 : 0; 
						informiereBeobachter();
					}
				});
				
				add(_checkbox);
				
			break;
			
			case ENUM:
				_enum = new JComboBox<String>();
				
				_enum.addItemListener(new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent arg0)
					{
						if(arg0.getStateChange() == ItemEvent.SELECTED)
						{
							_value = _enum.getSelectedIndex();
							informiereBeobachter();
						}
					}
				});
				
				add(_enum);
			break;
		}
	}
	
	public Eigenschaftsfeld(String beschriftung, int typ)
	{
		this(beschriftung, typ, 0);
	}
	
	public Eigenschaftsfeld(String beschriftung, int typ, Observer observer)
	{
		this(beschriftung, typ);
		_observer = observer;
	}
	
	public Eigenschaftsfeld(String beschriftung, int typ, int startwert, Observer observer)
	{
		this(beschriftung, typ, startwert);
		_observer = observer;
	}
	
	public Eigenschaftsfeld(String beschriftung, Object[] enumValues, int startwert)
	{
		this(beschriftung, ENUM, startwert);
		setEnumStrings(getStringsFromEnum(enumValues));
	}
	
	public Eigenschaftsfeld(String beschriftung, Object[] enumValues, int startwert, Observer observer)
	{
		this(beschriftung, enumValues, startwert);
		_observer = observer;
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
	
	
	private void setEnumStrings(String[] enumStrings)
	{
		if(_enum != null)
		{
			int valuebuffer = _value;
			//_enum.setModel(new JComboBox<String>(enumStrings).getModel());
			_enum.removeAllItems();
			for(String s : enumStrings)
			{
				_enum.addItem(s);
			}
			_value = valuebuffer <= enumStrings.length ? valuebuffer : enumStrings.length;
			_enum.setSelectedIndex(_value);
		}
	}
	
	private void informiereBeobachter()
	{
		if(_observer != null)
		{
			_observer.update(null, null);
		}
	}
	
	public int getWert()
	{
		return _value;
	}
}
