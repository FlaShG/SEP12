package de.uni_hamburg.informatik.sep.zuul.befehle;

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
	public void ausfuehren(SpielLogik logik)
	{
		SpielKontext kontext = logik.getKontext();
		if(getParameters().length == 0)
		{
			logik.schreibeNL(TextVerwalter.KEINORT);
			return;
		}

		String ort = getParameters()[0];

		if(ort.equals("tasche"))
		{

			Item item = kontext.getInventar().nehmeLetztesItem();

			int energie = kontext.getLebensEnergie();

			switch (item)
			{
			case Kuchen:
				energie += SpielLogik.KUCHEN_ENERGIE_GEWINN;
				logik.schreibeNL(TextVerwalter.kuchengegessentext(energie));
				break;
			case Giftkuchen:
				energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
				if(energie > 0)
				{
					logik.schreibeNL(TextVerwalter
							.giftkuchengegessentext(energie));
				}
				else
				{
					logik.beendeSpiel(TextVerwalter.KUCHENTODTEXT);
				}
				break;

			default:
				logik.schreibeNL(TextVerwalter.NICHTSZUMESSENTEXT);
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
				logik.schreibeNL(TextVerwalter
						.kuchenVomBodenGegessenText(energie));
				if(kontext.getAktuellerRaum().getNaechstesItem() != Item.Keins)
				{
					logik.schreibeNL(TextVerwalter.IMMERNOCHKUCHENTEXT);
				}
				
				break;
			case Giftkuchen:
				energie -= SpielLogik.GIFTKUCHEN_ENERGIE_VERLUST;
				kontext.getAktuellerRaum().loescheItem();
				if(energie > 0)
				{
					logik.schreibeNL(TextVerwalter
							.giftkuchenVomBodenGegessenText(energie));
				}
				else
				{
					logik.beendeSpiel(TextVerwalter.KUCHENTODTEXT);
				}
				break;
			default:
				logik.schreibeNL(TextVerwalter.NICHTSZUMESSENTEXTBODEN);
				break;
			}
			
			
			kontext.setLebensEnergie(energie);
		}
		else
		{
			logik.schreibeNL(TextVerwalter.KEINORT);
			return;
		}
		
		
	}
}
