package de.uni_hamburg.informatik.sep.zuul.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.ClientPaket;

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
	public boolean zeigeAn(ClientPaket raum) throws RemoteException;

}
