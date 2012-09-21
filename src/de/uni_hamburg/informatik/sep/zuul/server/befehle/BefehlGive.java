package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.Random;

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
			case UKuchen:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.LABOR_GESUNDER_KUCHEN);
				kontext.getInventar().fuegeItemHinzu(Item.IKuchen);
				break;
			case UGiftkuchen:
				Spiel.getInstance().schreibeNL(
						TextVerwalter.LABOR_GIFTIGER_KUCHEN);
				kontext.getInventar().fuegeItemHinzu(Item.IGiftkuchen);
				break;
			case IGiftkuchen:
				Spiel.getInstance()
						.schreibeNL(TextVerwalter.LABOR_KEIN_KRUEMEL);
				kontext.getInventar().fuegeItemHinzu(Item.IGiftkuchen);
				break;
			case IKuchen:
				Spiel.getInstance()
						.schreibeNL(TextVerwalter.LABOR_KEIN_KRUEMEL);
				kontext.getInventar().fuegeItemHinzu(Item.IKuchen);
				break;
			}

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
