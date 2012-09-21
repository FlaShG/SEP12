package de.uni_hamburg.informatik.sep.zuul.server.befehle;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;
import de.uni_hamburg.informatik.sep.zuul.spiel.IOManager;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

/**
 * Lädt eine Level über den FileChooser. Damit wird ein neues Spiel gestartet.
 * @author 1fechner
 *
 */
public class BefehlLoad extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			String level = FileChooser.oeffneDatei();
			if(level != null)
			{
				Spiel.getInstance().spielen(level);
			}
			else
			{
				Spiel.getInstance().schreibeNL("Vorgang abgebrochen oder ungültige Datei");
			}
		}
		else
		{
			String level = "./xml_dateien/" +  getParameters()[0] + ".xml";
			if(IOManager.validiereLevel(level))
			{
				Spiel.getInstance().spielen(level);
			}
			else 
			{
				Spiel.getInstance().schreibeNL("Diese Datei existiert nicht.");
			}
			
		}
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_LADEN;
	}

}
