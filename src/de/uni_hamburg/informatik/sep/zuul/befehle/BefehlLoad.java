package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlLoad extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_LADEN;
	}

}
