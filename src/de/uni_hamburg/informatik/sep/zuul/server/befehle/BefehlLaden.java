package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlLaden implements Befehl
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
		//TODO Level laden
		if(befehlszeile.getGeparsteZeile().size() == 1)
		{
			String level = null;
			final JFileChooser fileChooser = new JFileChooser("./xml_dateien/");

			int returnVal = fileChooser.showOpenDialog(new JPanel());

			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				level = file.getAbsolutePath();
			}
			//			Spiel.getInstance().spielen(level);
		}
		else
		{
			String level = befehlszeile.getGeparsteZeile().get(1);

			//			Spiel.getInstance().spielen("./xml_dateien/" + level + ".xml");
		}
		return true;
	}

	@Override
	public void gibFehlerAus(ServerKontext kontext, Spieler spieler,
			Befehlszeile befehlszeile)
	{
	}

	@Override
	public String[] getBefehlsnamen()
	{
		return new String[] { TextVerwalter.BEFEHL_LADEN };
	}

}
