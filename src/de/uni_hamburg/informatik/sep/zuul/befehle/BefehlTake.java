package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

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

		switch(raum.getNaechstesItem())
		{
			case Kuchen:
				kontext.getInventar().fuegeItemHinzu(Item.Kuchen);
				kontext.schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
				raum.loescheItem();
				if(raum.getNaechstesItem() != Item.Keins)
				{
					kontext.schreibeNL(TextVerwalter.IMMERNOCHKUCHENTEXT);
				}
			break;
			case Giftkuchen:
				kontext.getInventar().fuegeItemHinzu(Item.Giftkuchen);
				kontext.schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
				raum.loescheItem();
				if(raum.getNaechstesItem() != Item.Keins)
				{
					kontext.schreibeNL(TextVerwalter.IMMERNOCHKUCHENTEXT);
				}
			break;

			default:
				kontext.schreibeNL(TextVerwalter.NICHTSZUMNEHMENTEXT);
			break;
		}
	}
}
