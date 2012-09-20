package de.uni_hamburg.informatik.sep.zuul.server.npcs;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.util.PathFinder;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

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
		ArrayList<Raum> path = new PathFinder()
		{
			@Override
			protected boolean isZielRaum(Raum raum)
			{
				return SpielLogik.isRaumZielRaum(raum);
			}
		}.findPath(_raum);

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

}
