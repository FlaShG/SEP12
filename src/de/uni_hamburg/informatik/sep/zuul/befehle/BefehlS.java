package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlS extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.RICHTUNG_SUEDEN;
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"s"};
	}
	
	@Override
	public void ausfuehren(SpielLogik kontext)
	{
		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(TextVerwalter.RICHTUNG_SUEDEN);
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