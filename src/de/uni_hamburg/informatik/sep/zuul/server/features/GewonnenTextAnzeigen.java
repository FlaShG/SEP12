package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.server.util.TickListener;

public final class GewonnenTextAnzeigen implements Feature, TickListener
{
	@Override
	public boolean tick(SpielKontext kontext, boolean _)
	{
		if(kontext.getAktuellerRaum().getNaechstesItem() == Item.Gegengift)
		{
			SpielLogik.beendeSpiel(kontext, TextVerwalter.SIEGTEXT + "\n"
					+ TextVerwalter.BEENDENTEXT);
			return false;
		}
		return true;
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
	}
}