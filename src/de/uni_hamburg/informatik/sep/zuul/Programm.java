package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

public class Programm
{

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{
		boolean onconsole = args.length > 0 && args[0].equals("onconsole");
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
			//TODO: Console.
		}

	}

}
