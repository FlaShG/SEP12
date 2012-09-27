package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.uni_hamburg.informatik.sep.zuul.editor.EditorFenster;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

public abstract class PathFinder
{

	protected abstract boolean isZielRaum(Raum raum);

	public LinkedList<Raum> findPath(Raum start)
	{
		LinkedList<Raum> result = findPath(start, new HashMap<Raum, LinkedList<Raum>>(), new HashSet<Raum>());
		return result;
	}

	private LinkedList<Raum> findPath(Raum start, Map<Raum, LinkedList<Raum>> begangeneRaeume, Set<Raum> betrachtete)
	{
		//wenn für diesen Raum bereits ein kürzester Pfad gefunden wurde
		if(begangeneRaeume.get(start) != null)
		{
			//gib diesen zurück
			return new LinkedList<Raum>(begangeneRaeume.get(start));
		}
		
		if(start.getName().equals("int"))
		{
			System.out.println("");
		}
		
		LinkedList<Raum> kuerzesterPfadDiesesRaums = null;
		
		//wenn der Raum ein Ziel ist
		if(isZielRaum(start))
		{
			//ist der kürzeste Pfad trivial
			kuerzesterPfadDiesesRaums = new LinkedList<Raum>();
			kuerzesterPfadDiesesRaums.add(start);		
		}
		//ansonsten muss für diesen Raum ein kürzester Pfad gefunden werden
		else
		{
			betrachtete.add(start);
			ArrayList<Raum> ausgaenge = start.getAusgaenge();
			
			//überprüfe alle Ausgänge des Raumes
			for(Raum ausgang : ausgaenge)
			{
				LinkedList<Raum> kuerzesterPfadDiesesAusgangs = null;
				
				//wenn für den Raum nebenan schon ein gültiger Pfad gefunden wurde
				if(begangeneRaeume.get(ausgang) != null)
				{
					//benutze diesen
					kuerzesterPfadDiesesAusgangs = new LinkedList<Raum>(begangeneRaeume.get(ausgang));
				}
				//wenn wir mit weiterer Betrachtung des Raumes keinen Zyklus bilden
				else if(!betrachtete.contains(ausgang))
				{
					//finde dessen kürzesten Weg
					kuerzesterPfadDiesesAusgangs = findPath(ausgang, begangeneRaeume, betrachtete);
				}
				
				//wenn ein kürzester Pfad gefunden wurde
				if(kuerzesterPfadDiesesAusgangs != null)
				{
					//und dieser kürzer ist als von den anderen Ausgängen
					if(kuerzesterPfadDiesesRaums == null || kuerzesterPfadDiesesAusgangs.size() + 1 < kuerzesterPfadDiesesRaums.size())
					{
						//benutze ihn als Pfad für diesen Raum
						kuerzesterPfadDiesesRaums = kuerzesterPfadDiesesAusgangs;
						kuerzesterPfadDiesesRaums.add(0, start);
					}
				}
			}

			betrachtete.remove(start);
		}
		
		begangeneRaeume.put(start, kuerzesterPfadDiesesRaums);
		
		//fancy debugging view
		//start.setName(""+ (kuerzesterPfadDiesesRaums != null ? (kuerzesterPfadDiesesRaums.size()) : "X"));
		
		return kuerzesterPfadDiesesRaums == null ? null : new LinkedList<Raum>(kuerzesterPfadDiesesRaums);
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
