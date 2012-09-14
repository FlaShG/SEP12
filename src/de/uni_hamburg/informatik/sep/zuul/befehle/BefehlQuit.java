package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.TextVerwalter;

final class BefehlQuit extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return "quit";
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		kontext.beendeSpiel(TextVerwalter.BEENDENTEXT);
	}
}