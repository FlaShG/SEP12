package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.security.UnrecoverableKeyException;

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
		// Keine Parameter; nur "essen"
		if(getParameters().length == 0)
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINORT);
			return;
		}

		String ort = getParameters()[0];

		// Ein Parameter essen tasche, essen boden; random krümel?, frage nach 3. param?
		if(getParameters().length == 1)
		{
			if(ort.equals(TextVerwalter.ORT_TASCHE))
			{
				if(kontext.getInventar().hasAnyKuchen())
				{
					Item item = kontext.getInventar().getAnyKuchen();
					issZufaelligenKruemel(item, kontext);
				}
				else
				{
					Spiel.getInstance().schreibeNL(
							TextVerwalter.NICHTSZUMESSENTEXT);
				}
			}

			else if(ort.equals(TextVerwalter.ORT_BODEN))
			{
				if(kontext.getAktuellerRaum().getItems() != null)
				{
					Item item = kontext.getAktuellerRaum().getNaechstesItem();
					issZufaelligenKruemel(item, kontext);
					if(kontext.getAktuellerRaum().getNaechstesItem() != Item.Keins)
					{
						Spiel.getInstance().schreibeNL(
								TextVerwalter.IMMERNOCHKUCHENTEXT);
					}
				}
				else
				{
					Spiel.getInstance().schreibeNL(
							TextVerwalter.NICHTSZUMESSENTEXTBODEN);
				}
			}
		}

		// 2 Parameter, essen tasche krümel
		else if(getParameters().length == 2)
		{
			String itemParam = getParameters()[1];
			System.out.println(itemParam);

			if(itemParam != null && !itemParam.equals("krümel"))
			{
				Spiel.getInstance().schreibeNL("Das können sie nicht essen...");
			}
			Item item = kontext.getInventar().nehmeLetztesItem();

			issZufaelligenKruemel(item, kontext);

		}
		// 3 parameter; essen tasche guter krümel, essen tasche schlechter krümel
		else if(getParameters().length > 2)
		{
			String itemParam = getParameters()[1];
			String itemArt = getParameters()[2];

			switch (itemParam)
			{
			case "guter":
				if(itemArt.equals("krümel"))
				{

					if(kontext.getInventar().hatDiesenKuchen(Item.IKuchen))
					{
						kontext.getInventar().getKuchen(Item.IKuchen);
						int energie = kontext.getLebensEnergie();
						energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
						Spiel.getInstance().schreibeNL(
								TextVerwalter.kuchengegessentext(energie));
					}
					else
					{
						Spiel.getInstance().schreibeNL(
								TextVerwalter.KEINIDENTIFIZIERTERKUCHEN);
					}
				}
				break;
			case "schlechter":
				if(itemArt.equals("krümel"))
				{
					if(kontext.getInventar().hatDiesenKuchen(Item.IGiftkuchen))
					{
						kontext.getInventar().getKuchen(Item.IGiftkuchen);
						int energie = kontext.getLebensEnergie();
						energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
						Spiel.getInstance().schreibeNL(
								TextVerwalter.giftkuchengegessentext(energie));
					}
					else
					{
						Spiel.getInstance().schreibeNL(
								TextVerwalter.KEINIDENTIFIZIERTERKUCHEN);
					}
				}
				break;
			default:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.KEINIDENTIFIZIERTERKUCHEN);
				return;
			}
		}
		else
		{
			Spiel.getInstance().schreibeNL(TextVerwalter.KEINORT);
			return;
		}

	}

	/**
	 * Isst einen zufälligen Krümel, verändert so den Kontext und gibt die
	 * ensprechenden Texte auf
	 * 
	 * @param i
	 * @param kontext
	 */
	private void issZufaelligenKruemel(Item i, SpielKontext kontext)
	{
		Item item = i;
		int energie = kontext.getLebensEnergie();

		switch (item)
		{
		case IKuchen:
		case UKuchen:
			energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
			kontext.getAktuellerRaum().loescheItem();
			Spiel.getInstance().schreibeNL(
					TextVerwalter.kuchengegessentext(energie));

			break;
		case IGiftkuchen:
		case UGiftkuchen:
			energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
			kontext.getAktuellerRaum().loescheItem();
			if(energie > 0)
			{
				Spiel.getInstance().schreibeNL(
						TextVerwalter.giftkuchengegessentext(energie));
			}
			else
			{
				SpielLogik.beendeSpiel(kontext, TextVerwalter.KUCHENTODTEXT);
			}
			break;
		default:
			Spiel.getInstance().schreibeNL(TextVerwalter.DORTLIEGTNICHTS);
			break;
		}

		kontext.setLebensEnergie(energie);
	}
}
