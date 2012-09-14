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
				new BefehlQuit() };

		_map = new HashMap<String, Befehl>();
		for(Befehl befehl : befehle)
			_map.put(befehl.getBefehlsname(), befehl);

	}

	public static Befehl get(String befehlsname, String[] parameter)
	{
		Befehl befehl = _map.get(befehlsname);
		if(befehl == null)
		{
			befehl = unbekannnterBefehl;
		}
		
		befehl = befehl.clone();
		befehl.prepare(parameter);

		return befehl;
	}
}
