package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import de.uni_hamburg.informatik.sep.zuul.server.features.Katze;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlFuettereKatze implements Befehl
{

	public static final String BEFEHLSNAME = TextVerwalter.BEFEHL_FEED + " "
			+ "katze";

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);
		return raum.hasKatze() && spieler.getInventar().hasAnyKuchen();
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);

		Katze katze = raum.getKatze();

		if(katze.isSatt())
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.KATZE_HAT_KEINEN_HUNGER);
			return false;
		}
		Item kuchen = spieler.getInventar().getAnyKuchen();
		katze.fuettere(kontext, spieler, kuchen);

		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		Raum raum = kontext.getAktuellenRaumZu(spieler);

		if(!spieler.getInventar().hasAnyKuchen())
		{
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.MAUS_KEIN_KRUEMEL);
		}
		else if(!raum.hasKatze())
		{
			//TODO: Keine Katze zum Füttern!
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.BEFEHL_FEED_NICHTS_DA_ZUM_FUETTERN);
		}
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { BEFEHLSNAME };
	}

}
