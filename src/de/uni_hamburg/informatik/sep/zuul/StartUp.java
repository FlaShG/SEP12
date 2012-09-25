package de.uni_hamburg.informatik.sep.zuul;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.client.Client;
import de.uni_hamburg.informatik.sep.zuul.client.StartConsole;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.StartFenster;
import de.uni_hamburg.informatik.sep.zuul.server.Server;

public class StartUp
{
	static StartUp startUp;
	static Runnable runnable;
	protected Client _client;
	protected Server _server;
	
	public static void main(final String args[]) throws RemoteException,
			AlreadyBoundException, NumberFormatException,
			MalformedURLException, NotBoundException
	{
		runnable = new Runnable()
		{
			
			@Override
			public void run()
			{
				if(args.length == 1 && args[0].equals("console"))
				{
					try
					{
						startUp = new StartConsole();
					}
					catch(RemoteException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch(MalformedURLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch(AlreadyBoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch(NotBoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					startUp = new StartFenster();
				}
				
			}
		};
		runnable.run();
	}
	
	public static void restart()
	{
		// TODO shutdown server
		startUp._server.beendeServer();
		startUp._server = null;
		startUp._client.serverBeendet();
		startUp._client = null;
		System.gc();
		runnable.run();
	}
	
}
