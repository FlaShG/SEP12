package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

final class BefehlGo extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_GEHEN;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			kontext.schreibeNL(TextVerwalter.KEINERICHTUNG);
			return;
		}

		String richtung = getParameters()[0];

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(richtung);
		if(naechsterRaum == null)
		{
			kontext.schreibeNL(TextVerwalter.KEINETUER);
		}
		else
		{
			kontext.setAktuellerRaum(naechsterRaum);
		}
	}
}