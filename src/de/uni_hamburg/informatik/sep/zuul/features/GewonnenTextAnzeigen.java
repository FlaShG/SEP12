package de.uni_hamburg.informatik.sep.zuul.features;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

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