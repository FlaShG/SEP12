package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JPanel;

public class RaumEigenschaftenPanel extends JPanel
{
	private Eigenschaftsfeld _kuchen;
	
	public RaumEigenschaftenPanel()
	{
		
		add(_kuchen = new Eigenschaftsfeld("Krümel", false));
		add(_kuchen = new Eigenschaftsfeld("Giftkrümel", false));
		add(_kuchen = new Eigenschaftsfeld("Maus", true));
	}
}
