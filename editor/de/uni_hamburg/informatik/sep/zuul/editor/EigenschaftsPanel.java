package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Abstrakte Superklasse für Eigenschaftspanel - Beschriftete
 * Eingabekomponenten.
 * 
 * @author 0graeff
 * 
 */
public abstract class EigenschaftsPanel extends JPanel
{
	private EditorBeobachter _beobachter;

	protected EigenschaftsPanel(String beschriftung)
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);

		add(new JLabel(beschriftung));
	}

	protected EigenschaftsPanel(String beschriftung, EditorBeobachter beobachter)
	{
		this(beschriftung);
		_beobachter = beobachter;
	}

	protected final void informiereBeobachter()
	{
		if(_beobachter != null)
		{
			_beobachter.eigenschaftUpdate();
		}
	}
}
