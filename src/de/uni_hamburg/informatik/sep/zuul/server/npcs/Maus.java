package de.uni_hamburg.informatik.sep.zuul.server.npcs;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.util.PathFinder;

public class Maus
{

	private PathFinder _pathFinder;
	private Raum _aktuellerRaum;
	private Raum _endRaum;

	public Maus(Raum aktuellerRaum, Raum endRaum)
	{
		_aktuellerRaum = aktuellerRaum;
		_endRaum = endRaum;
	}

	/**
	 * @return the _richtung
	 */
	public String getRichtung()
	{
		_pathFinder = new PathFinder()
		{
			@Override
			protected boolean isZielRaum(Raum raum)
			{
				return raum == _endRaum;
			}
		};

		return PathFinder.getRichtung(_pathFinder.findPath(_aktuellerRaum));
	}

	public Raum getAktuellerRaum()
	{
		return _aktuellerRaum;
	}

	public void setAktuellerRaum(Raum raum)
	{
		_aktuellerRaum = raum;
	}

}
