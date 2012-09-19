package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Collection;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;

public class RaumEigenschaftenPanel extends JPanel
{
	private Eigenschaftsfeld _kuchen;
	private Eigenschaftsfeld _giftkuchen;
	private Eigenschaftsfeld _typ;
	
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
		
		//Gib ein Object-Array in Eigenschaftsfeld für eine JComboox
		add(_typ = new Eigenschaftsfeld("Raumtyp", RaumArt.values(), raum.getRaumart().ordinal(), observer));
		
		add(_kuchen = new Eigenschaftsfeld("Krümel", Eigenschaftsfeld.ZAHL, kuchen, observer));
		add(_giftkuchen = new Eigenschaftsfeld("Giftkrümel", Eigenschaftsfeld.ZAHL, giftkuchen, observer));
	}
	
	public int getKuchenzahl()
	{
		return _kuchen.getWert(); 
	}
	
	public int getGiftkuchenzahl()
	{
		return _giftkuchen.getWert();
	}
	
	public RaumArt getTyp()
	{
		return RaumArt.values()[_typ.getWert()];
	}
}
