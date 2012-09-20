package de.uni_hamburg.informatik.sep.zuul.multiplayer.server;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel.Spiel;

public class Programm
{

	private static boolean onconsole;

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{
		onconsole = args.length > 0 && args[0].equals("console");

		final Spiel spiel = Spiel.getInstance();

		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				spiel.spielen(null);

			}
		});

	}

	/**
	 * @return the onconsole
	 */
	public static boolean isOnconsole()
	{
		return onconsole;
	}

}
