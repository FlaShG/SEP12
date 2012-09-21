package de.uni_hamburg.informatik.sep.zuul.server.raum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.XmlRaum;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * Hält Informationen über die Raumverbindungen. Hier wird gespeichert, welche
 * Räume über welche Ausgänge wie verbunden sind.
 */
public class RaumStruktur
{

	private Map<Raum, Raum[]> _connected;

	/**
	 * Erzeuge eine neue Raumstruktur aus Räumen, die bereits verbunden sind!
	 * 
	 * @param list
	 *            Liste von verbundenen Räumen
	 */
	public RaumStruktur(List<Raum> list)
	{
		_connected = new HashMap<Raum, Raum[]>();
		initialisiereConnections(list);
	}

	/**
	 * Erzuege eine Struktur aus einer Liste von Xml-Struktur-Räumen sowie einer
	 * Liste von Räumen.
	 * 
	 * @param xmlList
	 *            die eingelesene Strukturliste
	 * @param list
	 *            die eingelesene Raumliste
	 */
	public RaumStruktur(List<XmlRaum> xmlList, List<Raum> list)
	{
		_connected = new HashMap<Raum, Raum[]>();
		Map<Integer, Raum> zuordnungen = erzeugeIDZuordnungen(xmlList, list);

		for(XmlRaum xmlRaum : xmlList)
		{
			//nördlicher Nachbar
			Raum nordNachbar = zuordnungen.get(xmlRaum.getNordID());

			//ostlicher Nachbar
			Raum ostNachbar = zuordnungen.get(xmlRaum.getOstID());

			//suedlicher Nachbar
			Raum suedNachbar = zuordnungen.get(xmlRaum.getSuedID());

			//westlicher Nachbar
			Raum westNachbar = zuordnungen.get(xmlRaum.getWestID());

			//der Raum selbst
			Raum raum = zuordnungen.get(xmlRaum.getID());
			raum.setKoordinaten(xmlRaum.getX(), xmlRaum.getY());

			Raum[] array = { nordNachbar, ostNachbar, suedNachbar, westNachbar };

			_connected.put(raum, array);
		}
	}

	/**
	 * Erzeugt eine Map von IDs und den zugehörigen Räumen. Die ID ist der Key
	 * zum Raum.
	 * 
	 * @param xmlList
	 *            Liste aus XMLRäumen
	 * @param list
	 *            Liste aus Räumen
	 */
	private Map<Integer, Raum> erzeugeIDZuordnungen(List<XmlRaum> xmlList,
			List<Raum> list)
	{
		Map<Integer, Raum> idNameZuordnung = new HashMap<Integer, Raum>();

		for(XmlRaum xmlRaum : xmlList)
		{
			for(Raum r : list)
			{
				if(r.getId() == xmlRaum.getID())
				{
					idNameZuordnung.put(r.getId(), r);
				}
			}
		}

		return idNameZuordnung;
	}

	/**
	 * Gibt eine Map mit allen Verbindungen zurück. Der Raum ist der Schlüssel,
	 * er liefert ein Array bestehend aus den Räumen nach Norden, Osten, Süden,
	 * Westen.
	 * 
	 * @return Map<Raum, [NordRaum, OstRaum, SuedRaum, WestRaum]>
	 */
	public Map<Raum, Raum[]> getConnections()
	{
		return _connected;
	}

	/**
	 * Initialisiert die VerbindungsMap mit den ausgelesenen Verbindungen aus
	 * den einzelnen Rauemen
	 */
	private void initialisiereConnections(List<Raum> raumList)
	{
		for(Raum raum : raumList)
		{
			String[] ausgaenge = raum.getMoeglicheAusgaenge();
			_connected.put(raum, getNachbarn(ausgaenge, raum));
		}
	}

	/**
	 * liefere die Nachbarn zu diesem Raum als geordnetes Array zurück.
	 * 
	 * @param richtungen
	 *            das StringArray mit den Ausgaengen
	 * @param raum
	 *            der Raum zu dem wir die Nachbarn suchen
	 * 
	 * @return geordnetes Array mit den Nachbarn [NordNachbar, OstNachbar,
	 *         SuedNachbar, WestNachbar]
	 */
	private Raum[] getNachbarn(String[] richtungen, Raum raum)
	{
		Raum nordNachbar = null;
		Raum ostNachbar = null;
		Raum suedNachbar = null;
		Raum westNachbar = null;

		for(String s : richtungen)
		{
			if(s.equals(TextVerwalter.RICHTUNG_NORDEN))
			{
				nordNachbar = raum.getAusgang(s);
			}
			else if(s.equals(TextVerwalter.RICHTUNG_OSTEN))
			{
				ostNachbar = raum.getAusgang(s);
			}
			else if(s.equals(TextVerwalter.RICHTUNG_SUEDEN))
			{
				suedNachbar = raum.getAusgang(s);
			}
			else if(s.equals(TextVerwalter.RICHTUNG_WESTEN))
			{
				westNachbar = raum.getAusgang(s);
			}
		}
		Raum[] result = { nordNachbar, ostNachbar, suedNachbar, westNachbar };
		return result;
	}

	/**
	 * Getter für die Struktur in Form einer Liste von XmlRäumen.
	 * 
	 * @return List<XmlRaum>
	 */
	public List<XmlRaum> getXMLRaumListe()
	{
		List<XmlRaum> result = new ArrayList<XmlRaum>();
		for(Raum raum : _connected.keySet())
		{
			int nord = 0, ost = 0, sued = 0, west = 0;

			if(_connected.get(raum)[0] != null)
			{
				nord = _connected.get(raum)[0].getId();
			}
			if(_connected.get(raum)[1] != null)
			{
				ost = _connected.get(raum)[1].getId();
			}
			if(_connected.get(raum)[2] != null)
			{
				sued = _connected.get(raum)[2].getId();
			}
			if(_connected.get(raum)[3] != null)
			{
				west = _connected.get(raum)[3].getId();
			}

			XmlRaum xmlRaum = new XmlRaum(raum.getId(), nord, ost, sued, west,
					raum.getX(), raum.getY());
			result.add(xmlRaum);

		}

		return result;
	}

	public List<Raum> getRaeume()
	{
		// TODO Auto-generated method stub
		return new ArrayList<Raum>(_connected.keySet());
	}
}
