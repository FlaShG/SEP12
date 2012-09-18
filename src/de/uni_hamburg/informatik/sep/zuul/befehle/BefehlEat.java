package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
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
		Item item = kontext.getInventar().nehmeLetztesItem();

		int energie = kontext.getLebensEnergie();
		
		switch(item)
		{
			case Kuchen:
				energie += SpielKontext.KUCHEN_ENERGIE_GEWINN;
				kontext.schreibeNL(TextVerwalter.kuchengegessentext(energie));
			break;
			case Giftkuchen:
				energie -= SpielKontext.GIFTKUCHEN_ENERGIE_VERLUST;
				if(energie > 0)
				{
					kontext.schreibeNL(TextVerwalter.giftkuchengegessentext(energie));
				}
				else
				{
					kontext.beendeSpiel(TextVerwalter.KUCHENTODTEXT);
				}
			break;

			default:
				kontext.schreibeNL(TextVerwalter.NICHTSZUMESSENTEXT);
			break;
		}
		
		kontext.setLebensEnergie(energie);
	}
}
