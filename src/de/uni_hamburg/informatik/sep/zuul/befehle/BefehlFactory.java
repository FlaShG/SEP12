package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.HashMap;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public class BefehlFactory
{
	static final Map<String, Befehl> _map;
	static final Befehl unbekannnterBefehl = new Befehl()
	{

		@Override
		public String getBefehlsname()
		{
			return null;
		}

		@Override
		public void ausfuehren(SpielKontext kontext)
		{
			kontext.schreibeNL(TextVerwalter.FALSCHEEINGABE);
		}
	};

	static
	{
		Befehl[] befehle = new Befehl[] { new BefehlGo(), new BefehlHelp(),
				new BefehlTake(), new BefehlEat(), 
				new BefehlQuit(), new BefehlN(), new BefehlW(), new BefehlS(), new BefehlE(), new BefehlGive() };

		_map = new HashMap<String, Befehl>();
		for(Befehl befehl : befehle)
			_map.put(befehl.getBefehlsname(), befehl);

		//Aliases
		_map.put("n", _map.get(TextVerwalter.RICHTUNG_NORDEN));
		_map.put("s", _map.get(TextVerwalter.RICHTUNG_SUEDEN));
		_map.put("w", _map.get(TextVerwalter.RICHTUNG_WESTEN));
		_map.put("o", _map.get(TextVerwalter.RICHTUNG_OSTEN));
	}

	/**
	 * Gibt ein Befehlsobjeckt anhand des Befehlsnamens zurück
	 * @param befehlsname Der Name des gewünschten Befehls
	 * @param parameter Die Parameter, mit denen der Befehl ausgeführt werden soll
	 * @return Das Befehlsobjekt
	 */
	public static Befehl get(String befehlsname, String[] parameter)
	{
		Befehl befehl = _map.get(befehlsname);
		if(befehl == null)
		{
			befehl = unbekannnterBefehl;
		}
		
		befehl = befehl.clone();
		befehl.setParameter(parameter);

		return befehl;
	}
}
