package de.uni_hamburg.informatik.sep.zuul.server.befehle;

final class BefehlW extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.RICHTUNG_WESTEN;
	}

	@Override
	public String[] getAliases()
	{
		return new String[] { "w" };
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = kontext.getAktuellerRaum().getAusgang(
				TextVerwalter.RICHTUNG_WESTEN);
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