package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Abstrakte Superklasse für Eigenschaftspanel - Beschriftete Eingabekomponenten.
 * @author 0graeff
 *
 */
public abstract class EigenschaftsPanel extends JPanel
{
	private Observer _beobachter;
	
	public EigenschaftsPanel(String beschriftung)
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
		
		add(new JLabel(beschriftung));
	}
	
	public EigenschaftsPanel(String beschriftung, Observer beobachter)
	{
		this(beschriftung);
		_beobachter = beobachter;
	}
	
	protected final void informiereBeobachter()
	{
		if(_beobachter != null)
		{
			_beobachter.update(null, null);
		}
	}
}
