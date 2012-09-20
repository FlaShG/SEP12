package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.npcs.util;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.TextVerwalter;



public class PathFinder
{
	private ArrayList<Raum> _raumListe;
	private String _richtung;
	
	public PathFinder(Raum startRaum, Raum zielRaum)
	{
		_raumListe = findPath(startRaum);
		_richtung = berechneRichtung(startRaum, zielRaum);
	}

	public ArrayList<Raum> findPath(Raum start)
	{
		return findPath(start, null);
	}

	private ArrayList<Raum> findPath(Raum start, ArrayList<Raum> begangeneRaeume)
	{
		if(begangeneRaeume == null)
		{
			begangeneRaeume = new ArrayList<>();
		}
		begangeneRaeume.add(start);

		if(isRaumZielRaum(start))
		{
			return begangeneRaeume;
		}

		ArrayList<Raum> ausgaenge = start.getAusgaenge();
		ausgaenge.removeAll(begangeneRaeume);

		if(ausgaenge.size() == 0)
			return null;

		ArrayList<Raum> kuerzesterWegZumZiel = null;
		for(Raum ausgang : ausgaenge)
		{
			ArrayList<Raum> path = findPath(ausgang,
					(ArrayList<Raum>) begangeneRaeume.clone());
			if(path != null)
			{
				if(kuerzesterWegZumZiel == null
						|| path.size() < kuerzesterWegZumZiel.size())
					kuerzesterWegZumZiel = path;
			}
		}

		if(kuerzesterWegZumZiel == null)
			return null;
		return kuerzesterWegZumZiel;

	}
	
	public ArrayList<Raum> getWegAlsRaumListe()
	{
		return _raumListe;
	}
	
	public String getRichtung()
	{
		return _richtung;
	}
	
	private boolean isRaumZielRaum(Raum raum)
	{
		//TODO Ugly!
		return raum.getRaumart() == RaumArt.Ende;
	}
	
	private String berechneRichtung(Raum startRaum, Raum zielRaum)
	{
		if(_raumListe != null)
		{

			if(startRaum.getAusgang(TextVerwalter.RICHTUNG_NORDEN) == _raumListe.get(1))
				return TextVerwalter.RICHTUNG_NORDEN;
			if(startRaum.getAusgang(TextVerwalter.RICHTUNG_OSTEN) == _raumListe.get(1))
				return TextVerwalter.RICHTUNG_OSTEN;
			if(startRaum.getAusgang(TextVerwalter.RICHTUNG_SUEDEN) == _raumListe.get(1))
				return TextVerwalter.RICHTUNG_SUEDEN;
			if(startRaum.getAusgang(TextVerwalter.RICHTUNG_WESTEN) == _raumListe.get(1))
				return TextVerwalter.RICHTUNG_WESTEN;
		}
		return "unkown";
	}
}
