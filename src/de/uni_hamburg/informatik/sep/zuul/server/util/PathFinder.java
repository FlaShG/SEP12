package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

public abstract class PathFinder
{

	protected abstract boolean isZielRaum(Raum raum);

	/**
	 * Gibt eine geordnente Liste zurück, beginnend mit dem
	 * startraum und endend mit dem zielraum. dazwischen liegen
	 * die räume welche auf dem kürzesten weg zum ziel durchgangen wurden.
	 * @param start startraum
	 * @return
	 */
	public ArrayList<Raum> findPath(Raum start)
	{
		return findPath(start, null);
	}


	private ArrayList<Raum> findPath(Raum start, ArrayList<Raum> begangeneRaeume)
	{
		if(begangeneRaeume == null)
		{
			begangeneRaeume = new ArrayList<Raum>();
		}
		begangeneRaeume.add(start);

		if(isZielRaum(start))
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
			ArrayList<Raum> path = findPath(ausgang, new ArrayList<Raum>(
					begangeneRaeume));
			if(path != null)
			{
				if(kuerzesterWegZumZiel == null
						|| path.size() < kuerzesterWegZumZiel.size())
					kuerzesterWegZumZiel = path;
			}
		}

		return kuerzesterWegZumZiel;

	}

	/**
	 * gibt die richtung zurück, in welcher der erste und zweite Raum
	 * der übergebenen Liste mit einander verbunden sind.
	 * @param raums raumliste
	 * @return verbindungsrichtung zwischen erstem und zweiten raum der liste.S
	 */
	public static String getRichtung(ArrayList<Raum> raums)
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
