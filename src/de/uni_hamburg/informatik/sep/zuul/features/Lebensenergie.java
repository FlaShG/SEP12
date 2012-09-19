package de.uni_hamburg.informatik.sep.zuul.features;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

final public class Lebensenergie implements Feature, TickListener,
		PropertyChangeListener
{
	@Override
	public boolean tick(SpielKontext kontext, boolean raumGeaendert)
	{
		if(kontext.getLebensEnergie() <= 0)
		{
			SpielLogik.beendeSpiel(kontext, TextVerwalter.NIEDERLAGETEXT);
			return false;
		}

		if(raumGeaendert)
			Spiel.getInstance().schreibeNL(
					TextVerwalter.RAUMWECHSELTEXT + kontext.getLebensEnergie());

		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		SpielKontext kontext = (SpielKontext) evt.getSource();

		kontext.setLebensEnergie(kontext.getLebensEnergie()
				- SpielLogik.RAUMWECHSEL_ENERGIE_KOSTEN);
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
		kontext.addPropertyChangeListener("AktuellerRaum", this);
	}
}