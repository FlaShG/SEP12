package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final class BefehlQuit extends Befehl
{
	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_BEENDEN;
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		kontext.beendeSpiel(TextVerwalter.BEENDENTEXT);
	}
}