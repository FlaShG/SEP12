package de.uni_hamburg.informatik.sep.zuul.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;

public interface ServerInterface extends Remote
{
	/**
	 * registriere einen Client an diesem Server. Gib true oder false zurück,
	 * wenn das Registrieren erfolgreich war oder nicht.
	 * 
	 * @param client
	 *            der zu registrierende Client
	 * @return erfolg beim Login
	 */
	public boolean loginClient(ClientInterface client, String name)
			throws RemoteException;

	/**
	 * entferne einen Client von diesem Server. Gib true oder false zurück wenn
	 * das Entfernen erfolgreich war oder nicht.
	 * 
	 * @param client
	 *            der zu entfernende Client
	 * @return erfolg beim Logou
	 */
	public boolean logoutClient(String name) throws RemoteException;

	/**
	 * Empfange vom Client die Nutereingaben in Form von Strings. Gib true oder
	 * false zurück, je nach dem ob die Übertragung fehlerfrei und erfolgreich
	 * war.
	 * 
	 * @param eingabe
	 *            die Nutzereingabe
	 * @return Übertragung erfolgreich bzw. fehlerfrei
	 */
	public boolean empfangeNutzerEingabe(String eingabe, String benutzerName)
			throws RemoteException;

	/**
	 * Empfange die Starteingabe des Clients. Wenn alle Clients gestartet haben,
	 * geht das spiel los.
	 * 
	 * @param benutzerName
	 *            Spielername
	 * @throws RemoteException
	 */
	public void empfangeStartEingabe(String benutzerName)
			throws RemoteException;

}
