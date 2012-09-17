package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Item;
import de.uni_hamburg.informatik.sep.zuul.Raum;
import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

public final class BefehlEat extends Befehl
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
				kontext.schreibeNL(TextVerwalter.giftkuchengegessentext(energie));
			break;

			default:
				kontext.schreibeNL(TextVerwalter.NICHTSZUMESSENTEXT);
			break;
		}
		
		kontext.setLebensEnergie(energie);
	}
}
