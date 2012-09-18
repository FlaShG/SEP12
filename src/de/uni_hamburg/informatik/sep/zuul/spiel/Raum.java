package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Ein Raum in der Welt von Zuul. Ein Raum ist mit anderen Räumen über Ausgänge
 * verbunden, die in unterschiedlichen Richtungen liegen. In manchen Räumen
 * liegen Items, die von dem Spieler automatisch eingesammelt werden.
 * Standardmäßig sind die Räume leer.
 */
public class Raum
{
	private String _beschreibung;
	private Map<String, Raum> _ausgaenge;
	private Stack<Item> _items; 	
	private Maus _maus;
	
	/**
	 * Erzeugt einen Raum mit einer Beschreibung. Ein Raum hat anfangs keine
	 * Ausgänge.
	 * 
	 * @param beschreibung
	 *            die Beschreibung des Raums.
	 * 
	 * @require beschreibung != null
	 */
	public Raum(String beschreibung)
	{
		assert beschreibung != null : "Vorbedingung verletzt: beschreibung != null";

		this._beschreibung = beschreibung;
		this._ausgaenge = new HashMap<String, Raum>();

		_items = new Stack<Item>();

	}

	/**
	 * Setzt einen Ausgang aus diesem Raum zu einem Nachbarraum.
	 * 
	 * @param richtung
	 *            die Richtung, in der ein Nachbarraum hinzugefügt wird.
	 * @param nachbar
	 *            der Nachbarraum.
	 * @require richtung != null
	 * @require nachbar != null
	 */
	public void setAusgang(String richtung, Raum nachbar)
	{
		assert richtung != null : "Vorbedingung verletzt: richtung != null";
		assert nachbar != null : "Vorbedingung verletzt: nachbar != null";

		_ausgaenge.put(richtung, nachbar);
	}
	
	

	/**
	 * Verbindet die beiden übergebenen Räume in der entsprechenden Richtung.
	 * Dabei wird Raum1 in Richtung richtung mit Raum2 verbunden und Raum2 in
	 * Richtung gegenRichtung mit Raum1 zurück verbunden.
	 * 
	 * @param richtung
	 *            die Verbindungsrichtung zu Raum2
	 * @param nachbar
	 *            der zu verbindende Raum
	 * @param gegenRichtung
	 *            die Verbindungsrichtung zu Raum1
	 */
	public void verbindeZweiRaeume(String richtung, Raum nachbar,
			String gegenRichtung)
	{
		this.setAusgang(richtung, nachbar);
		nachbar.setAusgang(gegenRichtung, this);

	}

	/**
	 * Gibt den Nachbarraum zurück, der in der angegebenen Richtung liegt. Wenn
	 * in der Richtung kein Nachbarraum liegt, wird <code>null</code>
	 * zurückgegeben.
	 * 
	 * @param richtung
	 *            die Richtung des Nachbarraums.
	 * @require richtung != null
	 */
	public Raum getAusgang(String richtung)
	{
		assert richtung != null : "Vorbedingung verletzt: richtung != null";

		return _ausgaenge.get(richtung);
	}
	
	public String[] getMoeglicheAusgaenge()
	{
		return _ausgaenge.keySet().toArray(new String[0]);
	}

	/**
	 * Setze ein Item in diesen Raum. Default ist {@link Item}.keins .
	 * 
	 * @param item
	 *            Das neue Item
	 *
	 * @require item != Item.Keins
	 */
	public void addItem(Item item)
	{
		assert item != Item.Keins : "Vorbedingung verletzt: item != Item.Keins";
		
		_items.push(item);
		
		Collections.shuffle(_items);
	}

	/**
	 * Entfernt das nächste Item aus dem Raum, wenn es eines gibt
	 */
	public void loescheItem()
	{
		if(!_items.empty())
			_items.pop();
	}

	/**
	 * Liefert die Beschreibung dieses Raums (die dem Konstruktor übergeben
	 * wurde).
	 * 
	 * @ensure ergebnis != null
	 */
	public String getBeschreibung()
	{
		return _beschreibung;
	}
	
	/**
	 * liefert das Nächste Item, entfernt es jedoch nicht
	 * @return Item
	 * @ensure Item != null
	 */
	public Item getNaechstesItem()
	{
		if (_items.empty())
		{
			return Item.Keins;
		}
		return _items.peek();
	}
	

	public boolean hasMaus()
	{
		return _maus != null;
	}
	
	/**
	 * @return the _maus
	 * @require hasMaus()
	 */
	public Maus getMaus()
	{
		assert hasMaus();
		
		return _maus;
	}

	/**
	 * @param _maus the _maus to set
	 */
	public void setMaus(Maus maus)
	{
		_maus = maus;
	}

	public boolean isLabor()
	{
		return false;
	}

}
