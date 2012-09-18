package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Eigenschaftsfeld extends JPanel
{
	public static final int ZAHL = 0;
	public static final int BOOLEAN = 1;
	public static final int ENUM = 2;
	
	private JCheckBox _checkbox;
	private JSpinner _zahl;
	private int _value = 0;
	
	public Eigenschaftsfeld(String beschriftung, int typ)
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
		
		add(new JLabel(beschriftung));
		
		switch(typ)
		{
			case ZAHL:
				_zahl = new JSpinner();
				_zahl.addChangeListener(new ChangeListener()
				{
					
					@Override
					public void stateChanged(ChangeEvent arg0)
					{
						_value = (Integer)_zahl.getValue();
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
				_checkbox.addItemListener(new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent arg0)
					{
						_value = arg0.getStateChange() == ItemEvent.SELECTED ? 1 : 0; 
					}
				});
				
				add(_checkbox);
				
			break;
			
			case ENUM:
				//TODO
			break;
		}
	}
	
	public int getWert()
	{
		return _value;
	}
}
