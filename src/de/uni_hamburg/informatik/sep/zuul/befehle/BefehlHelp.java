package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.SpielKontext;

final class BefehlHelp extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return "help";
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		kontext.schreibeNL("Sie haben sich verlaufen. Sie sind allein.");
		kontext.schreibeNL("Sie irren auf dem Unigelände herum.");
		kontext.schreibeNL("");
		kontext.schreibeNL("Ihnen stehen folgende Befehle zur Verfügung:");
		
		kontext.schreibe("  ");
		for(String gueltigerBefehl: BefehlFactory._map.keySet())
			kontext.schreibe(" "+gueltigerBefehl);
		kontext.schreibeNL("");

	}
}