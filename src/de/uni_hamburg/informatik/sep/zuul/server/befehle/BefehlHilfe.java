package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlHilfe implements Befehl
{
	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{

		BefehlFactory.schreibeNL(kontext, spieler, TextVerwalter.HILFETEXT);

		// essen | hilfe | nehmen | ost | n | o | w | süd | s | gib | gehe | nord | beenden | west 
		// gehe nehme gib essen
		// ost süd nord west
		// o s n w
		// hilfe beenden
		// REST

		ArrayList<String> befehle = new ArrayList<String>(
				BefehlFactory._map.keySet());

		ArrayList<String> aktionen = new ArrayList<String>();
		aktionen.add("gehe");
		aktionen.add("nehmen");
		aktionen.add("gib");
		aktionen.add("füttere");
		aktionen.add("essen");

		ArrayList<String> inventar = new ArrayList<String>();
		inventar.add("inventar");
		inventar.add("ablegen");

		ArrayList<String> bewegen = new ArrayList<String>();
		bewegen.add("ost");
		bewegen.add("süd");
		bewegen.add("nord");
		bewegen.add("west");

		ArrayList<String> kurzBefehle = new ArrayList<String>();
		kurzBefehle.add("o");
		kurzBefehle.add("s");
		kurzBefehle.add("n");
		kurzBefehle.add("w");

		ArrayList<String> system = new ArrayList<String>();
		system.add("hilfe");
		system.add("beenden");

		befehle.removeAll(aktionen);
		befehle.removeAll(inventar);
		befehle.removeAll(bewegen);
		befehle.removeAll(kurzBefehle);
		befehle.removeAll(system);

		BefehlFactory.schreibeNL(kontext, spieler, buildString(aktionen));
		BefehlFactory.schreibeNL(kontext, spieler, buildString(inventar));
		BefehlFactory.schreibeNL(kontext, spieler, buildString(bewegen));
		BefehlFactory.schreibeNL(kontext, spieler, buildString(kurzBefehle));
		BefehlFactory.schreibeNL(kontext, spieler, buildString(system));
		BefehlFactory.schreibeNL(kontext, spieler, buildString(befehle));

		return true;
	}

	/**
	 * @return
	 */
	String buildString(Iterable<String> befehle)
	{
		StringBuilder builder = new StringBuilder();
		for(String gueltigerBefehl : befehle)
		{
			builder.append(gueltigerBefehl);
			builder.append(" | ");
		}
		if(builder.length() > 0)
			builder.delete(builder.length() - 3, builder.length() - 1);
		return builder.toString();
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