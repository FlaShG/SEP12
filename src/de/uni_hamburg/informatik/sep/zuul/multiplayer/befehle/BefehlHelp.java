package de.uni_hamburg.informatik.sep.zuul.multiplayer.befehle;

import java.util.ArrayList;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlHelp extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_HILFE;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		Spiel.getInstance().schreibeNL(TextVerwalter.HILFETEXT);

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

		Spiel.getInstance().schreibeNL(buildString(aktionen));
		Spiel.getInstance().schreibeNL(buildString(inventar));
		Spiel.getInstance().schreibeNL(buildString(bewegen));
		Spiel.getInstance().schreibeNL(buildString(kurzBefehle));
		Spiel.getInstance().schreibeNL(buildString(system));
		Spiel.getInstance().schreibeNL(buildString(befehle));

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
}