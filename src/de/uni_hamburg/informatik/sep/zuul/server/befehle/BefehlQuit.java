package de.uni_hamburg.informatik.sep.zuul.server.befehle;

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
		SpielLogik.beendeSpiel(kontext, TextVerwalter.BEENDENTEXT);
	}
}