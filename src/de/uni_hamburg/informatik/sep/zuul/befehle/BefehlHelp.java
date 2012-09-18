package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.util.ArrayList;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlHelp extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_HILFE;
	}

	@Override
	public void ausfuehren(SpielLogik kontext)
	{
		kontext.schreibeNL(TextVerwalter.HILFETEXT);
		
		// essen | hilfe | nehmen | ost | n | o | w | süd | s | gib | gehe | nord | beenden | west 
		// gehe nehme gib essen
		// ost süd nord west
		// o s n w
		// hilfe beenden
		// REST
		
		
		ArrayList<String> befehle = new ArrayList<String>(BefehlFactory._map.keySet());
		
		ArrayList<String> aktionen = new ArrayList<String> ();
		aktionen.add("gehe");
		aktionen.add("nehmen");
		aktionen.add("gib");
		aktionen.add("essen");
		

		ArrayList<String> bewegen = new ArrayList<String> ();
		bewegen.add("ost");
		bewegen.add("süd");
		bewegen.add("nord");
		bewegen.add("west");
		

		ArrayList<String> kurzBefehle = new ArrayList<String> ();
		kurzBefehle.add("o");
		kurzBefehle.add("s");
		kurzBefehle.add("n");
		kurzBefehle.add("w");
		
		ArrayList<String> system = new ArrayList<String> ();
		system.add("hilfe");
		system.add("beenden");
		
		befehle.removeAll(aktionen);
		befehle.removeAll(bewegen);
		befehle.removeAll(kurzBefehle);
		befehle.removeAll(system);
		
		kontext.schreibeNL(buildString(aktionen));
		kontext.schreibeNL(buildString(bewegen));
		kontext.schreibeNL(buildString(kurzBefehle));
		kontext.schreibeNL(buildString(system));
		kontext.schreibeNL(buildString(befehle));

	}

	/**
	 * @return
	 */
	String buildString(Iterable<String> befehle)
	{
		StringBuilder builder = new StringBuilder();
		for(String gueltigerBefehl: befehle)
		{
			builder.append(gueltigerBefehl);
			builder.append(" | ");
		}
		if(builder.length()> 0)
			builder.delete(builder.length()-3, builder.length()-1);
		return builder.toString();
	}
}