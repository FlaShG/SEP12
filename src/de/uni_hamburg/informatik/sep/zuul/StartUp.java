package de.uni_hamburg.informatik.sep.zuul;

import java.awt.DefaultKeyboardFocusManager;
import java.awt.KeyboardFocusManager;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.client.Client;
import de.uni_hamburg.informatik.sep.zuul.client.ClientGUI;
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
		try
		{
			startUp._server.logoutClient(startUp._client.getClientName());
		}
		catch(RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startUp._server = null;
		startUp._client.serverBeendet();
		startUp._client = null;
		System.gc();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(new DefaultKeyboardFocusManager());
		runnable.run();
	}
	
	public void starteRMI(final String serverName, final String serverIP,
			final int port, final String clientName, final boolean serverStarten)
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
					_client = new ClientGUI(serverName, serverIP, port, clientName);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				//finally
				//{
				//	_ui.dispose();
				//}
				finally
				{
					beendeStartEingabe();
				}
			}
		};

		Thread rmiThread = new Thread(run, "ZuulRMIThread");
		rmiThread.start();
	}
	
	protected void beendeStartEingabe()
	{

	}
	
}
