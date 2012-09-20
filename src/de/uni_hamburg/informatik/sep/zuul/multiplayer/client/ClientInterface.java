package de.uni_hamburg.informatik.sep.zuul.multiplayer.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.ClientPaket;

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
	 * @param raum
	 */
	public void zeigeAn(ClientPaket raum) throws RemoteException;
	
	
}
