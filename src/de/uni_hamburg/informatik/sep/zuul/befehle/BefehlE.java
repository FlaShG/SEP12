package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

final class BefehlE extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.RICHTUNG_OSTEN;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(TextVerwalter.RICHTUNG_OSTEN);
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