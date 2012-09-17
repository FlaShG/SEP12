package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

public class Programm
{

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{
		boolean onconsole = args.length > 0 && args[0].equals("console");
		if(!onconsole)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					Spiel spiel = new SpielGUI();
					spiel.spielen();
				}
			});
		}
		else
		{
			Spiel spiel = new SpielConsole();
			spiel.spielen();
		}

	}

}
