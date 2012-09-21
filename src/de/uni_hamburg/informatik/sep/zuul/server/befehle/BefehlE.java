package de.uni_hamburg.informatik.sep.zuul.server.befehle;

final class BefehlE extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.RICHTUNG_OSTEN;
	}

	@Override
	public String[] getAliases()
	{
		return new String[] { "o" };
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(
				TextVerwalter.RICHTUNG_OSTEN);
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