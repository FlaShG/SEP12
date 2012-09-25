package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.server.Server;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class StartConsole
{

	public StartConsole() throws RemoteException, AlreadyBoundException,
			MalformedURLException, NotBoundException
	{
		consoleAnzeigen("Was wollen sie spielen?(einzelspiel oder multispiel): ");
		switch (consoleLesen())
		{
		case "einzelspiel":
		case "allein":
		case "e":
			new Server();
			new ClientConsole("RmiServer", "127.0.0.1", 1090, "Dr. Little");
			break;
		case "multispiel":
		case "mehrspieler":
		case "m":
		case "multiplayer":
			consoleAnzeigen("Wollen Sie denn Server starten(j/n): ");
			String server = consoleLesen();
			if(server.equals("j"))
			{
				new Server();
			}
			consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
			String ip = consoleLesen();
			consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_SERVERPORTLABEL);
			String port = consoleLesen();
			consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_NAMEPLABEL);
			String name = consoleLesen();
			new ClientConsole("RmiServer", ip, Integer.parseInt(port), name);
			break;

		}
	}

	private static String consoleLesen()
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(
				System.in));
		String zeile = null;
		try
		{
			zeile = console.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return zeile;
	}

	private static void consoleAnzeigen(String text)
	{
		System.out.print(text);

	}
}
