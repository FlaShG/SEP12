package de.uni_hamburg.informatik.sep.zuul.server.npcs;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.util.PathFinder;

public class Maus
{

	private String _richtung;
	private PathFinder _pathFinder;
	private Raum _aktuellerRaum;
	private Raum _endRaum;

	public Maus(Raum aktuellerRaum, Raum endRaum)
	{
		_aktuellerRaum = aktuellerRaum;
		_endRaum = endRaum;

		berechneNeuenWeg();
	}

	/**
	 * @return the _richtung
	 */
	public String getRichtung()
	{
		return _richtung;
	}

	public void berechneNeuenWeg()
	{
		_pathFinder = new PathFinder()
		{

			@Override
			protected boolean isZielRaum(Raum raum)
			{
				return raum == _endRaum;
			}
		};

		_richtung = PathFinder
				.getRichtung(_pathFinder.findPath(_aktuellerRaum));
	}

	public void setNeuerRaum(Raum neuerRaum)
	{
		_aktuellerRaum = neuerRaum;
		berechneNeuenWeg();
	}

}
