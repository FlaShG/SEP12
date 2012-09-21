package de.uni_hamburg.informatik.sep.zuul.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.uni_hamburg.informatik.sep.zuul.server.ServerInterface;

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
	ServerInterface _server;
	String _clientName;
	int _port;

	public Client(String serverName, String serverIP, int clientPort,
			String clientName) throws MalformedURLException, RemoteException,
			NotBoundException
	{
		_serverName = serverName;
		_serverIP = serverIP;
		_isSpielzuEnde = false;
		_port = clientPort;

		_server = (ServerInterface) Naming.lookup(_serverName);
		
		login();
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
		if(!_server
				.loginClient((ClientInterface) UnicastRemoteObject
						.exportObject(this, _port), _clientName))
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

	@Override
	public boolean zeigeAn(ClientPaket paket) throws RemoteException
	{
		return false;
	}

	public void verarbeiteEingabe(String eingabezeile) throws RemoteException
	{
		while(!_server.empfangeNutzerEingabe(eingabezeile, _clientName))
			;
	}

	/**
	 * 
	 * @param args
	 *            [Oberfläche, Servername, Serverip, Clientport, Clientname]
	 * @throws NotBoundException
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			MalformedURLException, RemoteException, NotBoundException
	{
		
		if (args.length == 5 && args[4].equals("console"))
		{
			new ClientConsole(args[0], args[1], Integer.parseInt(args[2]), args[3]);
		}
		else 
		{
			new ClientGUI(args[0], args[1], Integer.parseInt(args[2]), args[3]);
		}

	}
}
