package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlGib implements Befehl
//TODO: BefehlGibLabor
{

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return kontext.getAktuellenRaumZu(spieler).getRaumart() == RaumArt.Start;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		if(!spieler.getInventar().hasAnyKuchen())
		{
			kontext.schreibeAnSpieler(spieler, TextVerwalter.LABOR_KEIN_KRUEMEL);
			return false;
		}

		Item kuchen = spieler.getInventar().getAnyKuchen();
		switch (kuchen)
		{

		case UKuchen:
			kontext.schreibeAnSpieler(spieler,
					TextVerwalter.LABOR_GESUNDER_KUCHEN);
			spieler.getInventar().fuegeItemHinzu(Item.IKuchen);
			break;

		case UGiftkuchen:
			kontext.schreibeAnSpieler(spieler,
					TextVerwalter.LABOR_GIFTIGER_KUCHEN);
			spieler.getInventar().fuegeItemHinzu(Item.IGiftkuchen);
			break;
		case IKuchen:
		case IGiftkuchen:
			kontext.schreibeAnSpieler(spieler, TextVerwalter.LABOR_KEIN_KRUEMEL);
			spieler.getInventar().fuegeItemHinzu(kuchen);
		}
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		kontext.schreibeAnSpieler(spieler, TextVerwalter.BEFEHL_GIB_KEIN_OBJEKT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_GIB };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_GIVE;
	}

}
