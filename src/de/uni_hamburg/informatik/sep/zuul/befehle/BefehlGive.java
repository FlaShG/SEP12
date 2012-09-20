package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Item;
import de.uni_hamburg.informatik.sep.zuul.spiel.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlGive extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 3 && getParameters()[0].equals("mir")
				&& getParameters()[1].equals("mehr")
				&& getParameters()[2].equals("leben"))
		{
			kontext.setLebensEnergie(100);
			Spiel.getInstance().schreibeNL("Schwupp.");
			return;
		}
		
		if(kontext.getAktuellerRaum().getRaumart() == RaumArt.Start)
		{
			if(!kontext.getInventar().hasAnyKuchen())
			{
				Spiel.getInstance()
						.schreibeNL(TextVerwalter.LABOR_KEIN_KRUEMEL);
				return;
			}

			Item kuchen = kontext.getInventar().getAnyKuchen();
			switch (kuchen)
			{
			case Kuchen:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.LABOR_GESUNDER_KUCHEN);
				break;
			case Giftkuchen:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.LABOR_GIFTIGER_KUCHEN);
				break;
			}
			kontext.getInventar().fuegeItemHinzu(kuchen);
			return;
		}

		Spiel.getInstance().schreibeNL(TextVerwalter.BEFEHL_GIB_KEIN_OBJEKT);
		return;
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_GIB;
	}

}
