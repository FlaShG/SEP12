package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Item;
import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public final class BefehlTake extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_NEHMEN;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		Raum raum = kontext.getAktuellerRaum();

		switch (raum.getNaechstesItem())
		{
			case Kuchen:
				kontext.getInventar().fuegeItemHinzu(Item.Kuchen);
				kontext.schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			break;
			case Giftkuchen:
				kontext.getInventar().fuegeItemHinzu(Item.Giftkuchen);
				kontext.schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			break;

			default:
				kontext.schreibeNL(TextVerwalter.NICHTSZUMNEHMENTEXT);
			break;
		}
	}
}
