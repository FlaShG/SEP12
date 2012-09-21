package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
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
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.LABOR_KEIN_KRUEMEL);
			return false;
		}

		Item kuchen = spieler.getInventar().getAnyKuchen();
		switch (kuchen)
		{
		case Kuchen:
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.LABOR_GESUNDER_KUCHEN);
			break;
		case Giftkuchen:
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.LABOR_GIFTIGER_KUCHEN);
			break;
		}
		spieler.getInventar().fuegeItemHinzu(kuchen);
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		BefehlFactory.schreibeNL(kontext, spieler,
				TextVerwalter.BEFEHL_GIB_KEIN_OBJEKT);
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_GIB };
	}

}
