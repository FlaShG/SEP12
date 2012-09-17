package de.uni_hamburg.informatik.sep.zuul.befehle;

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
		kontext.schreibeNL(TextVerwalter.HILFETEXT);
		
		StringBuilder builder = new StringBuilder();
		for(String gueltigerBefehl: BefehlFactory._map.keySet())
		{
			builder.append(gueltigerBefehl);
			builder.append(" | ");
		}
		builder.delete(builder.length()-3, builder.length()-1);
		
		kontext.schreibeNL(builder.toString());

	}
}