package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Collection;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;

/**
 * Ein Panel, in dem alles steckt, das zum Setzen der Einstellungen eines Raumes benötigt wird.
 * Inklusive Löschen-Button.
 * @author 0graeff
 */
public class RaumEigenschaftenPanel extends JPanel
{
	private Eigenschaftsfeld _kuchen;
	private Eigenschaftsfeld _giftkuchen;
	private Eigenschaftsfeld _typ;
	
	/**
	 * Erzeugt ein neues Panel zum Einstellen der Eigenschaften des übergebenen Raumes
	 * @param raum Der Raum, der bearbeitet werden soll
	 * @param observer Der Observer, der über Änderungen informiert werden will
	 * 
	 * @require raum != null
	 */
	public RaumEigenschaftenPanel(Raum raum, Observer observer)
	{
		assert raum != null : "Vorbedingung verletzt: raum != null";
		
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
	
	/**
	 * Gibt die Anzahl der im Raum vorhandenen Kuchen zurück, die eingestellt wurde
	 */
	public int getKuchenzahl()
	{
		return _kuchen.getWert(); 
	}
	
	/**
	 * Gibt die Anzahl der im Raum vorhandenen giftigen Kuchen zurück, die eingestellt wurde
	 */
	public int getGiftkuchenzahl()
	{
		return _giftkuchen.getWert();
	}
	
	/**
	 * Gibt die RaumArt zurück, die eingestellt wurde
	 */
	public RaumArt getTyp()
	{
		return RaumArt.values()[_typ.getWert()];
	}
}
