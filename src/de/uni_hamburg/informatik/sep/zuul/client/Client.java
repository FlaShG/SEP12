package de.uni_hamburg.informatik.sep.zuul.client;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.uni_hamburg.informatik.sep.zuul.StartUp;
import de.uni_hamburg.informatik.sep.zuul.server.ServerInterface;

/**
 * Abstrakte Client-Klasse.
 */
public abstract class Client extends UnicastRemoteObject implements
		ClientInterface
{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5290157890129564784L;
	String _serverName;
	String _serverIP;
	boolean _isSpielzuEnde;
	ServerInterface _server;
	private String _clientName;

	public Client(String serverName, String serverIP, String clientName)
			throws MalformedURLException, RemoteException, NotBoundException
	{
		// choose anonymous port
		super(0);
		_serverName = serverName;
		_serverIP = serverIP;
		_isSpielzuEnde = false;

		_clientName = clientName;

		if(bestehtServer(serverName))
		{
			_server = (ServerInterface) Naming.lookup("//" + _serverIP + "/"
					+ _serverName);
		}
		else
		{
			serverNichtGefunden();
		}

		//login();
	}

	public void schreibeText(String text)
	{

	}

	public void run() throws RemoteException
	{

	}

	/**
	 * Führt das Spiel aus.
	 */
	@Override
	public void login() throws RemoteException
	{
		if(!_server.loginClient((ClientInterface) this, _clientName))
		{
			System.err.println("Fehler beim Anmelden!");
			//TODO ausgabe auf gui
		}
	}

	@Override
	public void logout() throws RemoteException
	{
		if(!_server.logoutClient(_clientName))
		{
			System.err.println("Fehler beim Ausloggen!");
			//TODO ausgabe auf gui
		}

	}

	public void verarbeiteEingabe(String eingabezeile) throws RemoteException
	{
		if(!_server.empfangeNutzerEingabe(eingabezeile, _clientName))
		{
			System.err.println("Fehler bei der Übertragung");
		}
	}

	public void serverBeendet()
	{

	}

	public String getClientName()
	{
		return _clientName;
	}

	public boolean bestehtServer(String serverName)
	{
		//geht anscheinend nicht besser
		try
		{
			LocateRegistry.getRegistry().lookup(serverName);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	protected void serverNichtGefunden()
	{

	}

}
