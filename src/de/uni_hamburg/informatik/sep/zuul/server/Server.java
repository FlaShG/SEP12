package de.uni_hamburg.informatik.sep.zuul.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;
import de.uni_hamburg.informatik.sep.zuul.client.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;

public class Server extends UnicastRemoteObject implements ServerInterface
{

	// Dummy

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1688218849488836203L;
	private Map<String, ClientInterface> _connectedClients;
	private Spiel _spiel;

	protected Server() throws RemoteException, AlreadyBoundException
	{
		super();

		Registry rmireg = LocateRegistry.createRegistry(1099);

		// Diesen Server in die Registry schreiben
		rmireg.bind("RmiServer", this);

		// Liste der verbundenen Clients anlegen
		_connectedClients = new HashMap<String, ClientInterface>();

		_spiel = new Spiel();
	}

	// sinnlos?
	public boolean sendeAenderungen(List<ClientPaket> paketListe)
			throws RemoteException
	{
		boolean result = true;

		for(ClientPaket paket : paketListe)
		{
			ClientInterface client = _connectedClients.get(paket
					.getSpielerName());
			client.zeigeAn(paket);
		}
		return result;
	}

	@Override
	public boolean loginClient(ClientInterface client, String name)
			throws RemoteException
	{
		boolean result;

		if(_connectedClients.containsKey(name)
				|| _connectedClients.containsValue(client))
		{
			result = false;
		}
		else
		{
			_connectedClients.put(name, client);
			if(!_spiel.isGestartet())
			{
				_spiel.meldeSpielerAn(name);
			}

			result = true;
			System.out.println("eingeloggt");
		}

		return result;

	}

	@Override
	public boolean logoutClient(String name) throws RemoteException
	{
		_connectedClients.remove(name);

		_spiel.meldeSpielerAb(name);

		return true;
	}

	//TODO: ugly!
	@Override
	public boolean empfangeNutzerEingabe(String eingabe, String benuzterName)
			throws RemoteException
	{
		_spiel.verarbeiteEingabe(benuzterName, eingabe);
		ArrayList<ClientPaket> paketListe = new ArrayList<ClientPaket>();
		for(String name : _connectedClients.keySet())
		{
			paketListe.add(_spiel.packePaket(name));
		}
		sendeAenderungen(paketListe);
		return true;
	}

	public static void main(String[] args) throws Exception
	{
		new Server();
	}
}