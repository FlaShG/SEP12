package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.util.LinkedList;
import java.util.List;

/**
 * Das Inventar nimmt {@link Item}s auf oder gibt sie heraus.
 * 
 * @author 1roebe, 1jost
 *
 */
public class Inventar
{
	private List<Item> _inhalt;
	
	/**
	 * Konstruktor des Inventars.
	 * Für näheres siehe {@link Inventar}
	 */
	public Inventar()
	{
		_inhalt = new LinkedList<Item>();
	}
	
	/**
	 * Fügt ein {@link Item} dem Inventar hinzu.
	 * 
	 * @param item
	 * 			Das hinzuzufügende Item
	 * 
	 * @require item != null
	 * @ensure isGefuellt() == true
	 */
	public void fuegeItemHinzu(Item item)
	{
		assert item != null : "Vorbedingung verletzt: item != null";
		
		if (item != Item.Keins)
			_inhalt.add(item);
	}
	
	/**
	 * Nimmt das zuletzt hinzugefügte Item aus dem Inventar.
	 * Es wird dabei aus dem Inventar entfernt.
	 * Wenn keines vorhanden ist, ist es {@link Item}.Keins.
	 * 
	 * @return	Das Item
	 */
	public Item nehmeLetztesItem()
	{
		if (isGefuellt())
		{
			return _inhalt.remove(_inhalt.size()-1);
		}
		else
		{
			return Item.Keins;
		}
	}

	/**
	 * Gibt an, ob das Inventar mindestens ein Item besitzt.
	 */
	public boolean isGefuellt()
	{
		return _inhalt.size() > 0;
	}
	
	/**
	 * Gibt das Inventar als Liste zurück.
	 */
	public List<Item> getInhaltsliste()
	{
		return _inhalt;
	}
	
	public boolean hasAnyKuchen()
	{
		for(Item item: _inhalt)
		{
			switch (item)
			{
			case Kuchen:
			case Giftkuchen:
				return true;
			default:
				break;
			}
		}
		return false;
	}
	
	/**
	 * @require hasAnyKuchen()
	 * @return
	 */
	public Item getAnyKuchen()
	{
		assert hasAnyKuchen();

		for(Item item: _inhalt)
		{
			switch (item)
			{	
			case Kuchen:
			case Giftkuchen:
				_inhalt.remove(item);
				return item;
			default:
				break;
			}
		}
		return null;
	}
}
