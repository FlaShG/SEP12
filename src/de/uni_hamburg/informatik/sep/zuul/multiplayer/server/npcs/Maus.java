package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.npcs;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.npcs.util.PathFinder;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.TextVerwalter;

public class Maus
{
	private Raum _raum;

	private String _richtung;

	public Maus(String richtung)
	{
		_richtung = richtung;
	}

	/**
	 * @return the _richtung
	 */
	public String getRichtung()
	{
		return _richtung;
		
		
		
		
		
		
		
		
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
