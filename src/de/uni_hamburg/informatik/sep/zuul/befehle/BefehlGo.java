package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;

final class BefehlGo extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return "go";
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			kontext.schreibeNL("Wohin möchten Sie gehen?");
			return;
		}

		String richtung = getParameters()[0];

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(richtung);
		if(naechsterRaum == null)
		{
			kontext.schreibeNL("Dort ist keine Tür!");
		}
		else
		{
			kontext.setAktuellerRaum(naechsterRaum);
			kontext.zeigeRaumbeschreibung();
		}
	}
}