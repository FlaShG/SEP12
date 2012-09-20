package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlTake extends Befehl
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
		case UKuchen:
			kontext.getInventar().fuegeItemHinzu(Item.UKuchen);
			Spiel.getInstance().schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			raum.loescheItem();
			if(raum.getNaechstesItem() != Item.Keins)
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}
			break;
		case IKuchen:
			kontext.getInventar().fuegeItemHinzu(Item.IKuchen);
			Spiel.getInstance().schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			raum.loescheItem();
			if(raum.getNaechstesItem() != Item.Keins)
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}
			break;
		case UGiftkuchen:
			kontext.getInventar().fuegeItemHinzu(Item.UGiftkuchen);
			Spiel.getInstance().schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			raum.loescheItem();
			if(raum.getNaechstesItem() != Item.Keins)
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}
			break;
		case IGiftkuchen:
			kontext.getInventar().fuegeItemHinzu(Item.IGiftkuchen);
			Spiel.getInstance().schreibeNL(TextVerwalter.KUCHENGENOMMENTEXT);
			raum.loescheItem();
			if(raum.getNaechstesItem() != Item.Keins)
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.IMMERNOCHKUCHENTEXT);
			}
			break;

		default:
			Spiel.getInstance().schreibeNL(TextVerwalter.NICHTSZUMNEHMENTEXT);
			break;
		}
	}
}
