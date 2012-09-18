package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Collection;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

public class RaumEigenschaftenPanel extends JPanel
{
	private Eigenschaftsfeld _kuchen;
	private Eigenschaftsfeld _giftkuchen;
	private Eigenschaftsfeld _maus;
	
	public RaumEigenschaftenPanel(Raum raum, Observer observer)
	{
		Collection<Item> items = raum.getItems();
		int kuchen = 0;
		int giftkuchen = 0;
		for(Item item : items)
		{
			switch(item)
			{
				case Kuchen:
					++kuchen;
				break;
				case Giftkuchen:
					++giftkuchen;
				break;
			}
		}
		
		add(_kuchen = new Eigenschaftsfeld("Krümel", Eigenschaftsfeld.ZAHL, kuchen, observer));
		add(_giftkuchen = new Eigenschaftsfeld("Giftkrümel", Eigenschaftsfeld.ZAHL, giftkuchen, observer));
		add(_maus = new Eigenschaftsfeld("Maus", Eigenschaftsfeld.BOOLEAN, raum.getMaus() != null ? 1 : 0, observer));
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
