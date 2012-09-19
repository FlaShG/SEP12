package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

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
				spiel.spielen();

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
