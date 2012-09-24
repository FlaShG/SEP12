package de.uni_hamburg.informatik.sep.zuul.server.features;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.BefehlFactory;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public final class KuchenImRaumTextAnzeigen implements Feature,
		BefehlAusgefuehrtListener
{
	@Override
	public boolean befehlAusgefuehrt(ServerKontext kontext, Spieler spieler, Befehl befehl,
			boolean hasRoomChanged)
	{
		if(hasRoomChanged)
		{
			switch (SpielLogik.getAktuellenRaumZu(kontext, spieler).getNaechstesItem())
			{
			case IKuchen:
			case UKuchen:
			case IGiftkuchen:
			case UGiftkuchen:
				BefehlFactory.schreibeNL(kontext, spieler,
						TextVerwalter.KUCHENIMRAUMTEXT);
			}
		}

		return true;
	}
}