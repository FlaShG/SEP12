package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

public abstract class PathFinder
{

	protected abstract boolean isZielRaum(Raum raum);

	public LinkedList<Raum> findPath(Raum start)
	{
		return findPath(start, new HashMap<Raum, LinkedList<Raum>>());
	}

	private LinkedList<Raum> findPath(Raum start, Map<Raum, LinkedList<Raum>> begangeneRaeume)
	{
		begangeneRaeume.put(start, null);
		
		ArrayList<Raum> ausgaenge = start.getAusgaenge();
		LinkedList<Raum> kuerzesterPfadDiesesRaums = null;
		for(Raum ausgang : ausgaenge)
		{
			LinkedList<Raum> kuerzesterPfadDiesesAusgangs;
			if(begangeneRaeume.containsKey(ausgang))
			{
				kuerzesterPfadDiesesAusgangs = begangeneRaeume.get(ausgang);
			}
			else
			{
				kuerzesterPfadDiesesAusgangs = findPath(ausgang, begangeneRaeume);
			}
			if(kuerzesterPfadDiesesAusgangs != null)
			{
				if(kuerzesterPfadDiesesRaums == null || kuerzesterPfadDiesesAusgangs.size() + 1 < kuerzesterPfadDiesesRaums.size())
				{
					kuerzesterPfadDiesesRaums = kuerzesterPfadDiesesAusgangs;
					kuerzesterPfadDiesesRaums.add(0, start);
				}
			}
		}
		begangeneRaeume.put(start, kuerzesterPfadDiesesRaums);
		
		return kuerzesterPfadDiesesRaums;
	}

	public static String getRichtung(List<Raum> raums)
	{
		if(raums == null || raums.size() < 2)
			return null;

		Raum raum1 = raums.get(0);
		Raum raum2 = raums.get(1);
		for(String richtung : raum1.getMoeglicheAusgaenge())
		{
			if(raum1.getAusgang(richtung) == raum2)
				return richtung;
		}

		return null;
	}
}
