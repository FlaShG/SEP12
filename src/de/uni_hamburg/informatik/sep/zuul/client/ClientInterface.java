package de.uni_hamburg.informatik.sep.zuul.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote
{

	/**
	 * registriert den Client beim Server
	 */
	public void login() throws RemoteException;

	/**
	 * trennt die Verbindung zum Server
	 */
	public void logout() throws RemoteException;

	/**
	 * registriere die änderungen am Client
	 * 
	 * @param raum
	 * @return
	 */
	public boolean zeigeAn(ClientPaket paket) throws RemoteException;

	/**
	 * Zeigt die Vorschau des nächsten Raumes an.
	 * 
	 * @param paket
	 * @return
	 * @throws RemoteException
	 */
	public boolean zeigeVorschau(ClientPaket paket) throws RemoteException;

	/**
	 * Kann vom Server aufgerufen werden, wenn alle wartenden Clients bereit
	 * sind. Dann wird das Spiel / die Oberfläche gestartet.
	 * 
	 * @throws RemoteException
	 */
	public void starteClientUI(ClientPaket paket) throws RemoteException;

}
