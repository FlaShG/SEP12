package de.uni_hamburg.informatik.sep.zuul;

import java.util.HashMap;
import java.util.Map;

/**
 * Ein Raum in der Welt von Zuul. Ein Raum ist mit anderen Räumen über Ausgänge
 * verbunden, die in unterschiedlichen Richtungen liegen.
 */
public class Raum
{
	private String _beschreibung;
	private Map<String, Raum> _ausgaenge;

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
	public void setzeAusgang(String richtung, Raum nachbar)
	{
		assert richtung != null : "Vorbedingung verletzt: richtung != null";
		assert nachbar != null : "Vorbedingung verletzt: nachbar != null";

		_ausgaenge.put(richtung, nachbar);
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
	public Raum gibAusgang(String richtung)
	{
		assert richtung != null : "Vorbedingung verletzt: richtung != null";

		return _ausgaenge.get(richtung);
	}
	
	public String[] gibMoeglicheAusgaenge()
	{
		return _ausgaenge.keySet().toArray(new String[0]);
	}

	/**
	 * Liefert die Beschreibung dieses Raums (die dem Konstruktor übergeben
	 * wurde).
	 * 
	 * @ensure ergebnis != null
	 */
	public String gibBeschreibung()
	{
		return _beschreibung;
	}
}
