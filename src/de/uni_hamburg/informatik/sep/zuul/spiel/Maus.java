package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.util.ArrayList;

public class Maus
{
	private Raum _raum;

	public Maus(Raum raum)
	{
		_raum = raum;
	}

	/**
	 * @return the _richtung
	 */
	public String getRichtung()
	{

		ArrayList<Raum> path = findPath(_raum, null);
		if(path != null)
		{			
			
			if(_raum.getAusgang(TextVerwalter.RICHTUNG_NORDEN) == path.get(1))
				return TextVerwalter.RICHTUNG_NORDEN;
			if(_raum.getAusgang(TextVerwalter.RICHTUNG_OSTEN) == path.get(1))
				return TextVerwalter.RICHTUNG_OSTEN;
			if(_raum.getAusgang(TextVerwalter.RICHTUNG_SUEDEN) == path.get(1))
				return TextVerwalter.RICHTUNG_SUEDEN;
			if(_raum.getAusgang(TextVerwalter.RICHTUNG_WESTEN) == path.get(1))
				return TextVerwalter.RICHTUNG_WESTEN;
		}
		return "unkown";

	}


	ArrayList<Raum> findPath(Raum start, ArrayList<Raum> begangeneRaeume)
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
			ArrayList<Raum> path = findPath(ausgang, (ArrayList<Raum>)begangeneRaeume.clone());
			if(path != null)
			{
				if(kuerzesterWegZumZiel == null || path.size() < kuerzesterWegZumZiel.size())
					kuerzesterWegZumZiel = path;
			}
		}

		if(kuerzesterWegZumZiel == null)
			return null;
		return kuerzesterWegZumZiel;

	}

}
