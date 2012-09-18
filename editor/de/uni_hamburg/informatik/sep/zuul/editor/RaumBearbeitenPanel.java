package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class RaumBearbeitenPanel extends JPanel
{
	private JTextArea _beschreibung;
	private RaumEigenschaftenPanel _eigenschaften;
	private JButton _verbinden;
	
	public RaumBearbeitenPanel()
	{
		setLayout(new BorderLayout());
		
		add(_beschreibung = new JTextArea(), BorderLayout.WEST);
		Dimension dim = new Dimension(400,80);
		_beschreibung.setSize(dim);
		_beschreibung.setPreferredSize(dim);
		_beschreibung.setMinimumSize(dim);
		add(_eigenschaften = new RaumEigenschaftenPanel(), BorderLayout.CENTER);
		add(_verbinden = new JButton("verbinden mit"), BorderLayout.EAST);
	}
}
