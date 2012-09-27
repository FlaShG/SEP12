package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.StartUp;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

final class BefehlBeenden implements Befehl
{

	@Override
	public boolean vorbedingungErfuellt(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		return true;
	}

	@Override
	public boolean ausfuehren(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
		if(spieler != null)
		{
			spieler.die();
			kontext.schreibeAnSpieler(spieler, TextVerwalter.BEENDENTEXT);
		}
		// TODO: Schließe Verbindung zu Spieler
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				StartUp.restart(true);
			}
		});

		return false;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_BEENDEN };
	}

	@Override
	public String getHilfe()
	{
		return TextVerwalter.HILFE_QUIT;
	}
}