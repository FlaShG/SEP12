package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlHilfe implements Befehl
{
	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFETEXT);

		// essen | hilfe | nehmen | ost | n | o | w | süd | s | gib | gehe | nord | beenden | west 
		// gehe nehme gib essen | schauen
		// ost süd nord west
		// o s n w
		// hilfe beenden
		// 

		// TODO : hilfetexte in interface auslagern
		if(befehlszeile.getGeparsteZeile().size() == 1)
		{
			BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFETEXT);
		}
		switch (befehlszeile.getGeparsteZeile().get(1))
		{
		case "essen":
			BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFE_EAT);
			break;
		case "gehen":
			BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFE_GO);
			break;
		case "gib":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_GIVE);
			break;
		case "füttern":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_FEED);
			break;
		case "hilfe":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_HELP);
			break;
		case "beenden":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_QUIT);
			break;
		case "laden":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_LOAD);
			break;
		case "inventar":
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.HILFE_INVENTAR);
			break;
		case "ablegen":
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.HILFE_ABLEGEN);
			break;
		case "schauen":
			BefehlFactory
					.schreibeNL(kontext, spieler, TextVerwalter.HILFE_LOOK);
			break;
		case "ausgänge":
			BefehlFactory.schreibeNL(kontext, spieler,
					TextVerwalter.HILFE_AUSGAENGE);
			break;
		default:
			BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFETEXT);
		}

		return true;
	}

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_HILFE };
	}
}