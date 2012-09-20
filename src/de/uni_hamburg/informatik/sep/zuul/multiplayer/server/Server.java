package de.uni_hamburg.informatik.sep.zuul.multiplayer.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.Client;

public interface Server extends Remote {
	/**
	 * registriere einen Client an diesem Server. Gib true oder false zurück,
	 * wenn das Registrieren erfolgreich war oder nicht.
	 * 
	 * @param client
	 *            der zu registrierende Client
	 * @return erfolg beim Login
	 */
	public boolean loginClient(Client client) throws RemoteException;

	/**
	 * entferne einen Client von diesem Server. Gib true oder false zurück wenn
	 * das Entfernen erfolgreich war oder nicht.
	 * 
	 * @param client
	 *            der zu entfernende Client
	 * @return erfolg beim Logou
	 */
	public boolean logoutClient(Client client) throws RemoteException;

	/**
	 * Empfange vom Client die Nutereingaben in Form von Strings. Gib true oder
	 * false zurück, je nach dem ob die Übertragung fehlerfrei und erfolgreich
	 * war.
	 * 
	 * @param eingabe
	 *            die Nutzereingabe
	 * @return Übertragung erfolgreich bzw. fehlerfrei
	 */
	public boolean empfangeNutzerEingabe(String eingabe) throws RemoteException;
}
