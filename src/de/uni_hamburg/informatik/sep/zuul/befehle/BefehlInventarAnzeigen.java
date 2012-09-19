package de.uni_hamburg.informatik.sep.zuul.befehle;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

/**
 * Zeigt den Inhalt des Inventars an. Wenn ein Kuchen oder ein giftiger Kuchen vorhanden ist wird ein Kuchen angezeigt
 * @author 1fechner
 *
 */
final class BefehlInventarAnzeigen extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		
		Spiel.getInstance().schreibeNL("Ihre Tasche enthält: " + kontext.getInventar().toString());
	}

	@Override
	public String getBefehlsname()
	{
		return "inventar";
	}

}
