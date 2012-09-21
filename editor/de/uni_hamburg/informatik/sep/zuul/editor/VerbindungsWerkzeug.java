package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Liest von den GridButtons die Räume aus und verbindet alle nebeneinander
 * liegenden Räume.
 * 
 * @author 1griese, 0ortmann
 * 
 */
//TODO: Das kann alles statisch geregelt werden und braucht prinzipiell kein extra Objekt
public class VerbindungsWerkzeug
{
	private EditorMap _map;
	private Raum[][] _raumArray;
	private int _arraySpaltenzahl; //Zeilenlänge
	private int _arrayZeilenzahl; //Spaltenlänge
	private List<Raum> _raumListe; //alle Räume in einer Liste

	/**
	 * Erzeuge ein neues Verbindungswerkzeug, welches aus der Map alle Räume
	 * ausliest und verbindet.
	 * 
	 * @param map
	 */
	public VerbindungsWerkzeug()
	{

	}

	/**
	 * Verbindet alle im Array gefundenen Räume in den entsprechenden
	 * Himmelsrichtungen mit ihren potenziellen Nachbarn
	 */
	public void verbindeRaeume(EditorMap map)
	{
		_map = map;
		GridButton[][] array = _map.getButtonArray();
		_arraySpaltenzahl = array.length;
		_arrayZeilenzahl = array[0].length;
		_raumListe = new ArrayList<Raum>();

		_raumArray = liesRaeumeAusButtonArray();

		for(int y = 0; y < _arrayZeilenzahl; ++y)
		{
			for(int x = 0; x < _arraySpaltenzahl; ++x)
			{
				if(existiertRaumAnPosition(x, y))
				{
					verbindeNachbarn(x, y);
					_raumListe.add(_raumArray[x][y]);
				}
			}
		}

	}

	/**
	 * Prüft ob Nachbarn vorhanden sind und verbindet diese
	 * 
	 * @param x
	 *            x Koordinate
	 * @param y
	 *            y Koordinate
	 */
	private void verbindeNachbarn(int x, int y)
	{
		//Nord
		if(istGueltigePosition(x, y - 1) && existiertRaumAnPosition(x, y - 1))
		{
			_raumArray[x][y].verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN,
					_raumArray[x][y - 1], TextVerwalter.RICHTUNG_SUEDEN);
		}

		//Ost
		if(istGueltigePosition(x + 1, y) && existiertRaumAnPosition(x + 1, y))
		{
			_raumArray[x][y].verbindeZweiRaeume(TextVerwalter.RICHTUNG_OSTEN,
					_raumArray[x + 1][y], TextVerwalter.RICHTUNG_WESTEN);
		}

		//Süd
		if(istGueltigePosition(x, y + 1) && existiertRaumAnPosition(x, y + 1))
		{
			_raumArray[x][y].verbindeZweiRaeume(TextVerwalter.RICHTUNG_SUEDEN,
					_raumArray[x][y + 1], TextVerwalter.RICHTUNG_NORDEN);
		}

		//West
		if(istGueltigePosition(x - 1, y) && existiertRaumAnPosition(x - 1, y))
		{
			_raumArray[x][y].verbindeZweiRaeume(TextVerwalter.RICHTUNG_WESTEN,
					_raumArray[x - 1][y], TextVerwalter.RICHTUNG_OSTEN);
		}
	}

	/**
	 * Hole aus den GridButtons die jeweiligen Raeume heraus. Gib sie als
	 * zweidimensionales Array zurück, so angeordnet wie die GridButtons.
	 * 
	 * @return Raum[][] die Räume aus den Buttons.
	 */
	private Raum[][] liesRaeumeAusButtonArray()
	{
		GridButton[][] array = _map.getButtonArray();
		Raum[][] raumArray = new Raum[_arraySpaltenzahl][_arrayZeilenzahl];
		for(int y = 0; y < _arrayZeilenzahl; ++y)
		{
			for(int x = 0; x < _arraySpaltenzahl; ++x)
			{
				raumArray[x][y] = array[x][y].getRaum();
			}
		}

		return raumArray;
	}

	/**
	 * Testet ob zwei Koordinaten eine gueltige Position im Array bilden.
	 * 
	 * @param x
	 *            x Koordinate
	 * @param y
	 *            y Koordinate
	 * @return true wenn gueltige Position
	 */
	private boolean istGueltigePosition(int x, int y)
	{
		return (x >= 0 && x < _arraySpaltenzahl && y >= 0 && y < _arrayZeilenzahl);
	}

	/**
	 * Wenn der Raum einen nachbar hat and der Arrayposition x, y, wird true
	 * zurückgegeben.
	 * 
	 * @param x
	 *            zu testende x Position
	 * @param y
	 *            zu testende y Position
	 * @return true wenn ein Raum vorhanden ist.
	 */
	private boolean existiertRaumAnPosition(int x, int y)
	{
		return (_raumArray[x][y] != null);
	}

	/**
	 * Getter für eine Liste mit allen Räumen aus dem Array. Es muss vorher
	 * {@link VerbindungsWerkzeug#verbindeRaeume()} aufgerufen werden, sonst
	 * liefert diese Methode eine leere Liste.
	 * 
	 * @return die Raumliste
	 */
	public List<Raum> getRaumListe()
	{
		return _raumListe;
	}

}
