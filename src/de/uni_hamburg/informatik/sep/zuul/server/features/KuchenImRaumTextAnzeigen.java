package de.uni_hamburg.informatik.sep.zuul.server.features;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.server.util.TickListener;

public final class KuchenImRaumTextAnzeigen implements Feature, TickListener,
		PropertyChangeListener
{

	@Override
	public boolean tick(SpielKontext kontext, boolean raumGeandert)
	{
		if(raumGeandert) // TODO: || KuchenAufgehoben
			switch (kontext.getAktuellerRaum().getNaechstesItem())
			{
			case Kuchen:
			case Giftkuchen:
				Spiel.getInstance().schreibeNL(TextVerwalter.KUCHENIMRAUMTEXT);
				break;
			}

		return true;
	}

	@Override
	public void registerToKontext(SpielKontext kontext)
	{
		// TODO Register Kuchen änderung!

		kontext.addTickListener(this);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{

	}
}