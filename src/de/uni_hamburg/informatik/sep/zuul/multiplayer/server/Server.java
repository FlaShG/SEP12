package de.uni_hamburg.informatik.sep.zuul.multiplayer.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.ClientInterface;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.spiel.Spiel;

public class Server extends UnicastRemoteObject implements ServerInterface
{

	// Dummy

	private Queue<ClientPaket> _clientChanges;
	private Map<String, ClientInterface> _connectedClients;
	private Spiel _spiel;

	protected Server() throws RemoteException, AlreadyBoundException
	{
		super();

		Registry rmireg = LocateRegistry.createRegistry(1099);

		// Diesen Server in die Registry schreiben
		rmireg.bind("RmiServer", this);

		// Queue für clientenänderungen anlegen
		_clientChanges = new LinkedList<ClientPaket>();

		// Liste der verbundenen Clients anlegen
		_connectedClients = new HashMap<String, ClientInterface>();

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
			if(!client.zeigeAn(paket))
			{
				result = false;
			}
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
			if(!_spiel.istGestartet())
			{
				_spiel.meldeSpielerAn(name);
			}

			result = true;
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

	@Override
	public boolean empfangeNutzerEingabe(String eingabe) throws RemoteException
	{
		_spiel.parseEingabezeile(eingabe);
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
