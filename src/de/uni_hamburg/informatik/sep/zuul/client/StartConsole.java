package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.StartUp;
import de.uni_hamburg.informatik.sep.zuul.server.Server;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class StartConsole extends StartUp
{

	public StartConsole() throws RemoteException, AlreadyBoundException,
			MalformedURLException, NotBoundException
	{
		consoleAnzeigen("Wie wollen sie spielen?(einzelspieler oder mehrspieler): ");
		String eingabe = consoleLesen();
		if(eingabe.equals("einzelspiel")
		|| eingabe.equals("einzelspieler")
		|| eingabe.equals("allein")
		|| eingabe.equals("e"))
		{
			consoleAnzeigen("Wollen Sie denn Server(Host) starten(j/n). Standard ist n: ");
			_server = new Server();
			_client = new ClientConsole("RmiServer", "127.0.0.1", 1090, "Dr. Little");
		}
		else if(eingabe.equals("multispiel")
		|| eingabe.equals("mehrspieler")
		|| eingabe.equals("multiplayer")
		|| eingabe.equals("m"))
		{
			String ip = "127.0.0.1";
			String port = "1090";
			consoleAnzeigen("Wollen sie einen Öffentliches Spiel erstellen?. Standard ist n: ");
			String server = consoleLesen();
			if(server.equals("j"))
			{
				_server = new Server();
			}
			else
			{
				consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
				ip = consoleLesen();
				consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_SERVERPORTLABEL);
				port = consoleLesen();
			}
			consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_NAMEPLABEL);
			String name = consoleLesen();
			_client = new ClientConsole("RmiServer", ip, Integer.parseInt(port), name);
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
