package de.uni_hamburg.informatik.sep.zuul;

import java.util.HashMap;
import java.util.Map;

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
	private Item _item;

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

		_item = Item.Keins;

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
		assert pruefeGegenrichtung(richtung, gegenRichtung) : "Vorbedingung verletzt: die Richtungen muessen gegenteilig sein.";
		this.setAusgang(richtung, nachbar);
		nachbar.setAusgang(gegenRichtung, this);
	}

	/**
	 * Prueft ob zwei Himmelsrichtungen gegenüberliegend sind. Es müssen
	 * englische Richtungsnamen verwendet werden.
	 * 
	 * @param richtung
	 * @param gegenRichtung
	 * @return true wenn gegenüberliegend.
	 */
	private boolean pruefeGegenrichtung(String richtung, String gegenRichtung)
	{
		Map<String, String> gegenTeile = new HashMap<String, String>();
		gegenTeile.put("north", "south");
		gegenTeile.put("south", "north");
		gegenTeile.put("east", "west");
		gegenTeile.put("west", "east");

		richtung.toLowerCase(); // einheitliche Zeichengröße
		gegenRichtung.toLowerCase();
		if(gegenTeile.get(richtung).equals(gegenRichtung))
		{
			return true;
		}
		return false;

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
	 * Gibt das Item zurück, welches der Raum hält. Gibt "keins" als Item
	 * zurück, wenn kein Item im Raum liegt.
	 * 
	 * @return item im Raum.
	 */
	public Item getItem()
	{
		return _item;
	}

	/**
	 * Setze ein Item in diesen Raum. Default ist {@link Item}.keins .
	 * 
	 * @param item
	 *            Das neue Item
	 */
	public void setItem(Item item)
	{
		_item = item;
	}

	/**
	 * Setze das Item dieses Raumes auf {@link Item}.keins . Dies ist auch der
	 * Raum-default Wert.
	 */
	public void loescheItem()
	{
		_item = Item.Keins;
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

}
