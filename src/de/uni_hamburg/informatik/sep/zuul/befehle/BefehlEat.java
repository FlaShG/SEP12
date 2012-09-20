package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlEat extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_ESSEN;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINORT);
			return;
		}

		String ort = getParameters()[0];
		String itemParam = null;
		if(getParameters().length > 1)
		{
			itemParam = getParameters()[1];
		}

		if(ort.equals("tasche"))
		{
			if(itemParam != null && !itemParam.equals("kuchen"))
			{
				Spiel.getInstance().schreibeNL("Das können sie nicht essen...");
			}
			Item item = kontext.getInventar().nehmeLetztesItem();

			int energie = kontext.getLebensEnergie();

			switch (item)
			{
			case Kuchen:
				energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
				Spiel.getInstance().schreibeNL(
						TextVerwalter.kuchengegessentext(energie));
				break;
			case Giftkuchen:
				energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
				if(energie > 0)
				{
					Spiel.getInstance().schreibeNL(
							TextVerwalter.giftkuchengegessentext(energie));
				}
				else
				{
					SpielLogik
							.beendeSpiel(kontext, TextVerwalter.KUCHENTODTEXT);
				}
				break;
				
			default:
				Spiel.getInstance()
						.schreibeNL(TextVerwalter.NICHTSZUMESSENTEXT);
				break;
			}

			kontext.setLebensEnergie(energie);
		}

		else if(ort.equals(TextVerwalter.ORT_BODEN))
		{
			Item item = kontext.getAktuellerRaum().getNaechstesItem();
			int energie = kontext.getLebensEnergie();

			switch (item)
			{
			case Kuchen:
				energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
				kontext.getAktuellerRaum().loescheItem();
				Spiel.getInstance().schreibeNL(
						TextVerwalter.kuchenVomBodenGegessenText(energie));
				if(kontext.getAktuellerRaum().getNaechstesItem() != Item.Keins)
				{
					Spiel.getInstance().schreibeNL(
							TextVerwalter.IMMERNOCHKUCHENTEXT);
				}

				break;
			case Giftkuchen:
				energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
				kontext.getAktuellerRaum().loescheItem();
				if(energie > 0)
				{
					Spiel.getInstance().schreibeNL(
							TextVerwalter
									.giftkuchenVomBodenGegessenText(energie));
				}
				else
				{
					SpielLogik
							.beendeSpiel(kontext, TextVerwalter.KUCHENTODTEXT);
				}
				break;
			default:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.NICHTSZUMESSENTEXTBODEN);
				break;
			}

			kontext.setLebensEnergie(energie);
		}
		else
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINORT);
			return;
		}

	}
}
