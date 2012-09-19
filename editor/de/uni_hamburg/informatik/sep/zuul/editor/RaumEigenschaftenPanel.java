package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Collection;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;

/**
 * Ein Panel, in dem Eigenschaftsfelder stecken,
 * die zum Setzen der Einstellungen eines Raumes benötigt werden.
 * @author 0graeff
 */
public class RaumEigenschaftenPanel extends JPanel
{
	private EigenschaftTextPanel _name;
	private EigenschaftIntPanel _kuchen;
	private EigenschaftIntPanel _giftkuchen;
	private EigenschaftEnumPanel _typ;
	
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
		
		add(_name = new EigenschaftTextPanel("Name", raum.getName()));
		//Gib ein Object-Array in Eigenschaftsfeld für eine JComboox
		add(_typ = new EigenschaftEnumPanel("Raumtyp", RaumArt.values(), raum.getRaumart().ordinal(), observer));
		
		add(_kuchen = new EigenschaftIntPanel("Krümel", kuchen, observer));
		add(_giftkuchen = new EigenschaftIntPanel("Giftkrümel", giftkuchen, observer));
	}
	
	/**
	 * Gibt den Namen zurück, die eingestellt wurde
	 */
	public String getRaumname()
	{
		return _name.getText();
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
