package de.uni_hamburg.informatik.sep.zuul.editor;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

/**
 * Liest von den GridButtons die Räume aus und verbindet alle nebeneinander
 * liegenden Räume.
 * 
 * @author 1griese, 0ortmann
 * 
 */
public class VerbindungsWerkzeug
{
	private EditorMap _map;
	private Raum[][] _raumArray;
	private int _arrayZL; //Zeilenlänge
	private int _arraySL; //Spaltenlänge

	/**
	 * Erzeuge ein neues Verbindungswerkzeug, welches aus der Map alle Räume
	 * ausliest und verbindet.
	 * 
	 * @param map
	 */
	public VerbindungsWerkzeug(EditorMap map)
	{
		_map = map;
		GridButton[][] array = _map.getButtonArray();
		_arrayZL = array.length;
		_arraySL = array[0].length;

	}

	public void verbindeRaeume()
	{
		_raumArray = liesRaeumeAusButtonArray();
		
		for(int i = 0; i < _arrayZL; ++i)
		{
			for(int j = 0; j < _arraySL; ++j)
			{
				//Nord
				if(istGueltigePosition(i-1, j) && existiertRaumAnPosition(i-1, j))
				{
					_raumArray[i][j].verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN, _raumArray[i-1][j], TextVerwalter.RICHTUNG_SUEDEN);
				}
				
				//Ost
				if(istGueltigePosition(i, j+1) && existiertRaumAnPosition(i, j+1))
				{
					_raumArray[i][j].verbindeZweiRaeume(TextVerwalter.RICHTUNG_OSTEN, _raumArray[i-1][j], TextVerwalter.RICHTUNG_WESTEN);
				}
				
				//Süd
				if(istGueltigePosition(i+1, j) && existiertRaumAnPosition(i+1, j))
				{
					_raumArray[i][j].verbindeZweiRaeume(TextVerwalter.RICHTUNG_SUEDEN, _raumArray[i-1][j], TextVerwalter.RICHTUNG_NORDEN);
				}
				
				//West
				if(istGueltigePosition(i, j-1) && existiertRaumAnPosition(i, j-1))
				{
					_raumArray[i][j].verbindeZweiRaeume(TextVerwalter.RICHTUNG_WESTEN, _raumArray[i-1][j], TextVerwalter.RICHTUNG_OSTEN);
				}
			}
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
		Raum[][] raumArray = new Raum[_arrayZL][_arraySL];
		for(int i = 0; i < _arrayZL; ++i)
		{
			for(int j = 0; j < _arraySL; ++j)
			{
				raumArray[i][j] = array[i][j].getRaum();
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
		return (x >= 0 && x <= _arrayZL && y >= 0 && y <= _arraySL);
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
	

}
