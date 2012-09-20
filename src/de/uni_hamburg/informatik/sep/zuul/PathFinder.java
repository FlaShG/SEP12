package de.uni_hamburg.informatik.sep.zuul;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;

public abstract class PathFinder
{

	protected abstract boolean isZielRaum(Raum raum);

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

		if(SpielLogik.isRaumZielRaum(start))
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
}