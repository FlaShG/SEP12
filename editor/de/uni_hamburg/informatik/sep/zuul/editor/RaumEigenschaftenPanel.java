package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JPanel;

public class RaumEigenschaftenPanel extends JPanel
{
	private Eigenschaftsfeld _kuchen;
	private Eigenschaftsfeld _giftkuchen;
	private Eigenschaftsfeld _maus;
	
	public RaumEigenschaftenPanel()
	{
		add(_kuchen = new Eigenschaftsfeld("Krümel", Eigenschaftsfeld.ZAHL));
		add(_giftkuchen = new Eigenschaftsfeld("Giftkrümel", Eigenschaftsfeld.ZAHL));
		add(_maus = new Eigenschaftsfeld("Maus", Eigenschaftsfeld.BOOLEAN));
	}
	
	public int getKuchenzahl()
	{
		return _kuchen.getWert();
	}
	
	public int getGiftkuchenzahl()
	{
		return _giftkuchen.getWert();
	}
	
	public boolean getMaus()
	{
		return _maus.getWert() == 1;
	}
}
