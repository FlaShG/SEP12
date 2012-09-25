package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.Collection;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;

/**
 * Ein Panel, in dem Eigenschaftsfelder stecken, die zum Setzen der
 * Einstellungen eines {@link Raum}es benötigt werden.
 * 
 * @author 0graeff
 */
public class RaumEigenschaftenPanel extends JPanel
{
	private EigenschaftTextPanel _name;
	private EigenschaftIntPanel _kuchen;
	private EigenschaftIntPanel _giftkuchen;
	private EigenschaftEnumPanel _typ;

	/**
	 * Erzeugt ein neues Panel zum Einstellen der Eigenschaften des übergebenen
	 * {@link Raum}es.
	 * 
	 * @param raum der {@link Raum}, der bearbeitet werden soll.
	 * @param beobachter der {@link EditorBeobachter}, der über Änderungen informiert werden will.
	 * 
	 * @require raum != null
	 */
	public RaumEigenschaftenPanel(Raum raum, EditorBeobachter beobachter)
	{
		assert raum != null : "Vorbedingung verletzt: raum != null";

		Collection<Item> items = raum.getItems();
		int kuchen = 0;
		int giftkuchen = 0;
		for(Item item : items)
		{
			switch (item)
			{
				case UKuchen:
					++kuchen;
				break;
				case UGiftkuchen:
					++giftkuchen;
				break;
			}
		}

		add(_name = new EigenschaftTextPanel("Name", raum.getName(), beobachter));
		add(_typ = new EigenschaftEnumPanel("Raumtyp", RaumArt.values(), raum
				.getRaumart().ordinal(), beobachter));

		add(_kuchen = new EigenschaftIntPanel("Krümel", kuchen, beobachter));
		add(_giftkuchen = new EigenschaftIntPanel("Giftkrümel", giftkuchen,
				beobachter));
	}

	/**
	 * Gibt den Namen zurück, der eingestellt wurde.
	 */
	public String getRaumname()
	{
		return _name.getText();
	}

	/**
	 * Gibt die Anzahl der im {@link Raum} vorhandenen Kuchen zurück, die eingestellt
	 * wurde.
	 */
	public int getKuchenzahl()
	{
		return _kuchen.getWert();
	}

	/**
	 * Gibt die Anzahl der im {@link Raum} vorhandenen giftigen Kuchen zurück, die
	 * eingestellt wurde.
	 */
	public int getGiftkuchenzahl()
	{
		return _giftkuchen.getWert();
	}

	/**
	 * Gibt die {@link RaumArt} zurück, die eingestellt wurde.
	 */
	public RaumArt getTyp()
	{
		return RaumArt.values()[_typ.getWert()];
	}
}
