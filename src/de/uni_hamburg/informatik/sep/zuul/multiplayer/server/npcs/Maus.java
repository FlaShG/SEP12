package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.npcs;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.npcs.util.PathFinder;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;


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
		_pathFinder = new PathFinder(_aktuellerRaum, _endRaum);
		
		_richtung = _pathFinder.getRichtung();
	}
	
	public void setNeuerRaum(Raum neuerRaum)
	{
		_aktuellerRaum = neuerRaum;
		berechneNeuenWeg();
	}

}
