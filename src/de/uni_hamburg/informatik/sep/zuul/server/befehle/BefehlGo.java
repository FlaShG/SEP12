package de.uni_hamburg.informatik.sep.zuul.server.befehle;

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
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINERICHTUNG);
			return;
		}

		String richtung = getParameters()[0];

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(richtung);
		if(naechsterRaum == null)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINETUER);
		}
		else
		{
			kontext.setAktuellerRaum(naechsterRaum);
		}
	}
}