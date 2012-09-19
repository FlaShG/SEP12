package de.uni_hamburg.informatik.sep.zuul.befehle;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileView;

import de.uni_hamburg.informatik.sep.zuul.Spiel;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class BefehlLoad extends Befehl
{

	@Override
	public void ausfuehren(SpielKontext kontext)
	{
		if(getParameters().length == 0)
		{
			String level = null;
			final JFileChooser fileChooser = new JFileChooser("./xml_dateien/");

			int returnVal = fileChooser.showOpenDialog(new JPanel());

			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				level = file.getAbsolutePath();
				System.out.println(level);
			}
			Spiel.getInstance().spielen(level);
		}
		else
		{
			String level = getParameters()[0];

			Spiel.getInstance().spielen("./xml_dateien/" + level + ".xml");
		}
	}

	@Override
	public String getBefehlsname()
	{
		return TextVerwalter.BEFEHL_LADEN;
	}

}
