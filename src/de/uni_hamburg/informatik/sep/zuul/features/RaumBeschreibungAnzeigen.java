package de.uni_hamburg.informatik.sep.zuul.features;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;

public final class RaumBeschreibungAnzeigen implements Feature,
		TickListener, PropertyChangeListener
{
	private boolean _raumGeaendertDurchLetztenBefehl;

	@Override
	public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
	{
		if(_raumGeaendertDurchLetztenBefehl)
			RaumBeschreibungAnzeigen.zeigeRaumbeschreibung(kontext);

		_raumGeaendertDurchLetztenBefehl = false;
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		_raumGeaendertDurchLetztenBefehl = true;
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		kontext.addTickListener(this);
		kontext.addPropertyChangeListener("AktuellerRaum", this);
	}

	/**
	 * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
	 * befindet.
	 */
	public static void zeigeRaumbeschreibung(SpielKontext kontext)
	{
		Spiel.getInstance().schreibeNL(
				kontext.getAktuellerRaum().getBeschreibung());
	}
}