package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.SpielKontext;

final class BefehlQuit extends Befehl
{
	@Override
	public String gibBefehlsname()
	{
		return "quit";
	}

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		kontext.beendeSpiel();
	}
}