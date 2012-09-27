package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.StartUp;
import de.uni_hamburg.informatik.sep.zuul.server.Server;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class StartConsole extends StartUp
{

	private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(
			System.in));

	public StartConsole() throws RemoteException, AlreadyBoundException,
			MalformedURLException, NotBoundException
	{
		consoleAnzeigen("Wie wollen sie spielen?(einzelspieler oder mehrspieler): ");
		String eingabe = consoleLesen();
		if(eingabe.equals("einzelspiel") || eingabe.equals("einzelspieler")
				|| eingabe.equals("allein") || eingabe.equals("e"))
		{

			consoleAnzeigen("Möchten Sie eine andere Karte Laden? (j/n)");

			if(consoleLesen().equals("j"))
			{
				ladeLevel();
			}

			starteRMI("RmiServer", "localhost", "Dr. Little", true);
		}
		else if(eingabe.equals("multispiel") || eingabe.equals("mehrspieler")
				|| eingabe.equals("multiplayer") || eingabe.equals("m"))
		{
			consoleAnzeigen("Ihr Name: ");

			String clientName = consoleLesen();

			String ip = "localhost";
			consoleAnzeigen("Wollen Sie einen Öffentliches Spiel erstellen?(j/n)");
			boolean serverStarten = consoleLesen().equals("j");
			if(serverStarten)
			{
				ladeLevel();
			}
			else
			{
				consoleAnzeigen(TextVerwalter.MODUS_AUSWAHL_SERVERIPLABEL);
				ip = consoleLesen();
			}
			starteRMI("RmiServer", ip, clientName, serverStarten);
		}
	}

	private static String consoleLesen()
	{
		String zeile = null;
		try
		{
			zeile = CONSOLE.readLine();
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

	private void ladeLevel()
	{
		consoleAnzeigen("Geben Sie den Namen der Datei an. Sie muss im Level-Ordner liegen: ");

		String eingabe = consoleLesen();

		eingabe = "./level/" + eingabe;

		File f = new File(eingabe);

		if(f.exists())
		{
			SpielLogik._levelPfad = eingabe;
		}
		else
		{
			System.out.println("Kann die Datei nicht finden.");
			ladeLevel();
		}
	}

	public void starteRMI(final String serverName, final String serverIP,
			final String clientName, final boolean serverStarten)
	{
		Runnable run = new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					if(serverStarten)
					{
						_server = new Server();
					}
					_client = new ClientConsole(serverName, serverIP,
							clientName);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}

			}
		};

		Thread rmiThread = new Thread(run, "ZuulRMIThread");
		rmiThread.start();
	}
}
