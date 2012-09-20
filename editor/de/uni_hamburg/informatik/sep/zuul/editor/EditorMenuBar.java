package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Die Menülsiete, die die Buttons "neu", "laden", "speichern" und "speichern unter" hält.
 * @author 0graeff
 *
 */
public class EditorMenuBar extends JPanel
{
	private JButton _new;
	private JButton _save;
	private JButton _saveas;
	private JButton _load;
	
	public EditorMenuBar()
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
		
		add(_new = new JButton("Neu"));
		add(_load = new JButton("Laden"));
		add(_save = new JButton("Speichern"));
		add(_saveas = new JButton("Speichern unter")); 
	}
	
	public JButton getNeuButton()
	{
		return _new;
	}
	
	public JButton getSpeicherButton()
	{
		return _save;
	}
	
	public JButton getLadenButton()
	{
		return _load;
	}
}
