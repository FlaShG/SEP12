package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

/**
 * Dieser Befehl zeigt die möglichen Ausgänge aus einem Raum manuell an.
 * @author 1fechner
 *
 */
public class BefehlAusgaenge extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		String[] ausgaenge = kontext.getAktuellerRaum().getMoeglicheAusgaenge();
		String output = "Ausgänge: ";

		for (String s : ausgaenge)
		{
			output += s + " ";
		}
		
		Spiel.getInstance().schreibeNL(output);
	}

	@Override
	public String getBefehlsname()
	{
		return "ausgänge";
	}
	
}
