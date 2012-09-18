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
		if(SpielKontext.IsRaumZielRaum(start))
			return begangeneRaeume;

		if(begangeneRaeume == null)
		{
			begangeneRaeume = new ArrayList<>();
		}
		begangeneRaeume.add(start);

		ArrayList<Raum> ausgaenge = start.getAusgaenge();
		ausgaenge.removeAll(begangeneRaeume);
		
		if(ausgaenge.size() == 0)
			return null;

		ArrayList<Raum> kuerzesterWeg = null;
		for(Raum ausgang : ausgaenge)
		{
			ArrayList<Raum> path = findPath(ausgang, begangeneRaeume);
			if(path != null)
			{
				if(kuerzesterWeg == null || path.size() < kuerzesterWeg.size())
					kuerzesterWeg = path;
			}
		}

		if(kuerzesterWeg == null)
			return null;
		begangeneRaeume.addAll(kuerzesterWeg);
		return begangeneRaeume;

	}

}
