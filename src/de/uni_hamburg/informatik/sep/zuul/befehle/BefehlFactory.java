package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public final class BefehlFactory
{
	static final Map<String, Befehl> _map;

	static
	{
		Befehl[] befehle = new Befehl[] { new BefehlGo(), new BefehlHelp(),
				new BefehlTake(), new BefehlEat(), new BefehlLoad(),
				new BefehlQuit(), new BefehlN(), new BefehlW(), new BefehlS(),
				new BefehlE(), new BefehlGive(), new BefehlInventarAnzeigen(),
				new BefehlFeed(), new BefehlAblegen() };

		_map = new HashMap<String, Befehl>();
		for(Befehl befehl : befehle)
		{
			for(String alias : befehl.getBefehlsnamen())
			{
				_map.put(alias, befehl);
			}
		}
	}

	/**
	 * Gibt ein Befehlsobjeckt anhand des Befehlsnamens zurück
	 * 
	 * @param befehlsname
	 *            Der Name des gewünschten Befehls
	 * @param parameter
	 *            Die Parameter, mit denen der Befehl ausgeführt werden soll
	 * @return Das Befehlsobjekt
	 */
	public static boolean fuehreBefehlAus(ServerKontext serverKontext, SpielerKontext kontext, Befehlszeile befehlszeile)
	{
		List<String> geparsteZeile = befehlszeile.getGeparsteZeile();
		Collection<String> befehlsnamen = moeglicheBefehlsnamen(geparsteZeile);
		
		Befehl letzterBefehl = null;
		for(String befehlsname : befehlsnamen)
		{
			Befehl befehl = _map.get(befehlsname);
			
			if(befehl != null)
			{
				letzterBefehl = befehl;
				if(befehl.vorbedingungErfuellt(serverKontext, kontext, befehlszeile))
				{
					return befehl.ausfuehren(serverKontext, kontext, befehlszeile);
				}
			}
		}
		
		if(letzterBefehl!=null)
		{
			letzterBefehl.gibFehlerAus(serverKontext, kontext, befehlszeile);
		}
		else
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.FALSCHEEINGABE);
		}
		
		return false;
	}

	private static Collection<String> moeglicheBefehlsnamen(List<String> geparsteZeile)
	{
		ArrayList<String> befehlsnamen = new ArrayList<String>();

		if(geparsteZeile.size() == 0)
			return befehlsnamen;
		
		befehlsnamen.add(geparsteZeile.get(0));
		
		for(int i=1; i< geparsteZeile.size(); i++)
		{
			String aktuellerToken = geparsteZeile.get(i);
			ArrayList<String> letzteToken = (ArrayList<String>) befehlsnamen.clone();
			letzteToken.add(aktuellerToken);
			befehlsnamen.add(Befehlszeile.join(letzteToken));
		}
		Collections.reverse(befehlsnamen);
		return befehlsnamen;
	}
}
