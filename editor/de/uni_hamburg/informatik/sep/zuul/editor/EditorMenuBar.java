package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;



public class EditorMenuBar extends JPanel
{
	private JButton _new;
	private JButton _save;
	private JButton _load;
	
	public EditorMenuBar()
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
		
		add(_new = new JButton("Neu"));
		add(_load = new JButton("Laden"));
		add(_save = new JButton("Speichern")); 
	}
}
