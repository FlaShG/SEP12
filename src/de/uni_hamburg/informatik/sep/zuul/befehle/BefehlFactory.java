package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.HashMap;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;

public class BefehlFactory
{
	static final Map<String, Befehl> _map;
	
	

	static
	{
		Befehl[] befehle = new Befehl[] {

		new Befehl()
		{

			@Override
			public String gibBefehlsname()
			{
				return "go";
			}

			@Override
			public void ausfuehren(SpielKontext kontext)
			{
				if(_parameter.length == 0)
				{
					// Gibt es kein zweites Wort, wissen wir nicht, wohin...
					kontext.schreibeNL("Wohin möchten Sie gehen?");
					return;
				}

				String richtung = _parameter[0];

				// Wir versuchen den Raum zu verlassen.
				Raum naechsterRaum = kontext.getAktuellerRaum().gibAusgang(richtung);
				if(naechsterRaum == null)
				{
					kontext.schreibeNL("Dort ist keine Tür!");
				}
				else
				{
					kontext.setAktuellerRaum(naechsterRaum);
				}
			}
		},

		new Befehl()
		{

			@Override
			public String gibBefehlsname()
			{
				return "help";
			}

			@Override
			public void ausfuehren(SpielKontext kontext)
			{
				kontext.schreibeNL("Sie haben sich verlaufen. Sie sind allein.");
				kontext.schreibeNL("Sie irren auf dem Unigelände herum.");
				kontext.schreibeNL("");
				kontext.schreibeNL("Ihnen stehen folgende Befehle zur Verfügung:");
				
				kontext.schreibe("  ");
				for(String gueltigerBefehl: _map.keySet())
					kontext.schreibe(" "+gueltigerBefehl);
				kontext.schreibeNL("");

			}
		},

		new Befehl()
		{

			@Override
			public String gibBefehlsname()
			{
				return "quit";
			}

			@Override
			public void ausfuehren(SpielKontext kontext)
			{
				kontext.beendeSpiel();
			}
		}};

		_map = new HashMap<String, Befehl>();
		for(Befehl befehl : befehle)
			_map.put(befehl.gibBefehlsname(), befehl);

	}

	public static Befehl get(String befehlsname, String[] parameter)
	{
		Befehl befehl = _map.get(befehlsname);
		if(befehl == null)
		{
			befehl = new Befehl()
			{
				
				@Override
				public String gibBefehlsname()
				{
					return null;
				}
				
				@Override
				public void ausfuehren(SpielKontext kontext)
				{
					kontext.schreibeNL("Ich weiß nicht, was Sie meinen...");
				}
			};
		}
		befehl.prepare(parameter);


		return befehl;
	}
}