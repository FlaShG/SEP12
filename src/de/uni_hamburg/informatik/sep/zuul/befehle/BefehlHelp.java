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
		
		for(String gueltigerBefehl: BefehlFactory._map.keySet())
			kontext.schreibe(" "+gueltigerBefehl);
		kontext.schreibeNL("");

	}
}