package de.uni_hamburg.informatik.sep.zuul;

import java.awt.DefaultKeyboardFocusManager;
import java.awt.KeyboardFocusManager;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.client.Client;
import de.uni_hamburg.informatik.sep.zuul.client.StartConsole;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.StartFenster;
import de.uni_hamburg.informatik.sep.zuul.server.Server;

public class StartUp
{
	static StartUp startUp;
	static Runnable runnable;
	static String[] _param;
	protected Client _client;
	protected Server _server;

	public static void main(final String args[]) throws RemoteException,
			AlreadyBoundException, NumberFormatException,
			MalformedURLException, NotBoundException
	{
		_param = args;

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
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					startUp = new StartFenster();
				}

			}
		};
		tryToRun();
	}

	/**
	 * 
	 */
	static void tryToRun()
	{
		try
		{
			runnable.run();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			System.exit(-1);
		}
	}

	public static void restart(boolean ausloggen)
	{

		if(ausloggen)
		{
			try
			{
				startUp._client.logout();
			}
			catch(RemoteException e)
			{
				e.printStackTrace();
			}
		}
		startUp._server = null;
		startUp._client = null;
		startUp = null;
		
		System.gc();
		KeyboardFocusManager
				.setCurrentKeyboardFocusManager(new DefaultKeyboardFocusManager());

		try
		{
			StartUp.main(StartUp._param);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
