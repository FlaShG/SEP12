package de.uni_hamburg.informatik.sep.zuul.client;

import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.server.Server;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * Das Ausgangssystem basiert auf einem Beispielprojekt aus dem Buch
 * "Java lernen mit BlueJ" von D. J. Barnes und M. Kölling.
 */
public abstract class Client implements ClientInterface
{
	String _serverName;
	String _serverIP;
	boolean _isSpielzuEnde;
	Server _server;

	public Client(String serverName, String serverIP)
	{
		_serverName = serverName;
		_serverIP = serverIP;
		_isSpielzuEnde = false;
	}
	
	protected void sendeBefehl(String nachricht)
	{
		try
		{
			_server.empfangeNutzerEingabe(nachricht);
		}
		catch(RemoteException e)
		{
			// TODO: Handle remote exceptions
			e.printStackTrace();
		}
	}

	public abstract void run();

	@Override
	public void login() throws RemoteException
	{
		//TODO: Log In into server
	}

	@Override
	public void logout() throws RemoteException
	{

	}
}
