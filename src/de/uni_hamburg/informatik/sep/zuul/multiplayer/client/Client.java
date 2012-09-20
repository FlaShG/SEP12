package de.uni_hamburg.informatik.sep.zuul.multiplayer.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.ClientRaum;

public interface Client extends Remote
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
	public void zeigeAn(ClientRaum raum) throws RemoteException;
	
	
}
