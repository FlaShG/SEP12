package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.server.util.TickListener;

public final class MausImRaumTextAnzeigen implements SpielerFeature, BefehlAusgefuehrtListener
{
	@Override
	public boolean tick(SpielKontext kontext, boolean raumGeandert)
	{
		// Maus
		if(raumGeandert && kontext.getAktuellerRaum().hasMaus())
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_GEFUNDEN);
			Spiel.getInstance().schreibeNL(TextVerwalter.MAUS_FRAGE);
		}
		return true;
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
	}
}