package de.uni_hamburg.informatik.sep.zuul.client;

import javax.swing.SwingUtilities;

public class Programm
{

	public static Client client;

	/**
	 * main-Methode zum Ausführen.
	 */
	public static void main(String[] args)
	{

		//TODO Server erstellen

		String serverIP = null;
		String serverName = null;
		// TODO Client erstellen, Ip und Servername übergeben
		createClient(args, serverName, serverIP);

		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				//TODO Server erstellen

				// TODO Client erstellen, Ip und Servername übergeben

				client.run();

			}
		});

	}

	private static void createClient(String[] args, String serverName,
			String serverIP)
	{
		boolean onconsole = args.length > 0 && args[0].equals("console");

		//TODO Server erstellen

		// TODO Client erstellen, Ip und Servername übergeben

		if(onconsole)
		{
			client = new ClientConsole(serverName, serverIP);
		}
		else
		{
			client = new ClientGUI(serverName, serverIP);
		}
	}
}
