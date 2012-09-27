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
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;
import de.uni_hamburg.informatik.sep.zuul.client.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spiel;

public class Server extends UnicastRemoteObject implements ServerInterface,
		Observer
{

	// Dummy

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1688218849488836203L;
	private Map<String, ClientInterface> _connectedClients;
	private List<String> _readyClients; //Liste der Namen der Spieler die bereit sind.
	private Spiel _spiel;
	private String _hostName;
	private Registry _rmireg;

	public Server() throws RemoteException, AlreadyBoundException
	{
		super();

		try
		{
			_rmireg = LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e)
		{
			_rmireg = LocateRegistry.getRegistry(1099);
		}

		// Diesen Server in die Registry schreiben
		_rmireg.bind("RmiServer", this);

		// Liste der verbundenen Clients anlegen
		_connectedClients = new HashMap<String, ClientInterface>();

		_readyClients = new ArrayList<String>();

		_spiel = new Spiel();
		_spiel.addObserver(this);
	}

	public void beendeServer()
	{
		try
		{
			_rmireg.unbind("RmiServer");
		}
		catch(Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			if(client != null)
				client.zeigeAn(paket);
		}
		return result;
	}

	@Override
	public boolean loginClient(ClientInterface client, String name)
			throws RemoteException
	{
		boolean result;

		//Der Host connected sich zuerst
		if(_connectedClients.isEmpty())
		{
			_hostName = name;
		}

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

		if(name.equals(_hostName))
		{
			for(ClientInterface client : _connectedClients.values())
			{
				client.serverBeendet();
			}

		}
		if(_connectedClients.isEmpty())
			beendeServer();

		return true;
	}

	@Override
	public boolean empfangeNutzerEingabe(String eingabe, String benuzterName)
			throws RemoteException
	{
		_spiel.verarbeiteEingabe(benuzterName, eingabe);
		sendeAenderungenAnAlle();
		return true;
	}

	@Override
	public void empfangeStartEingabe(String benutzerName)
			throws RemoteException
	{
		_readyClients.add(benutzerName);
		tryStarteSpiel();
	}

	private void tryStarteSpiel() throws RemoteException
	{
		if(alleGestartet())
		{
			starteAlleClients();
			_spiel.spielen();
			sendeAenderungenAnAlle();
		}
	}

	/**
	 * Sendet an alle Clients, dass das Spiel nun los geht. Diese warten nun
	 * nicht länger, sondern starten.
	 */
	private void starteAlleClients() throws RemoteException
	{
		for(Entry<String, ClientInterface> entry : _connectedClients.entrySet())
		{
			String name = entry.getKey();
			ClientInterface clientInterface = entry.getValue();
			clientInterface.starteClientUI(_spiel.packePaket(name));
		}
	}

	private boolean alleGestartet()
	{
		return _readyClients.containsAll(_connectedClients.keySet());
	}

	/**
	 * Broacaste die aktuellen Clinetpakete an alle regsitrierten Clients.
	 * 
	 * @throws RemoteException
	 */
	private void sendeAenderungenAnAlle() throws RemoteException
	{
		ArrayList<ClientPaket> paketListe = new ArrayList<ClientPaket>();
		for(String name : _connectedClients.keySet())
		{
			paketListe.add(_spiel.packePaket(name));
		}
		sendeAenderungen(paketListe);
	}

	public Map<String, ClientInterface> getConnectedClients()
	{
		return _connectedClients;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		//arg1 ist der name (String) des Spielers der den schauen Befehl ausgeführt hat.
		if(arg1 == null)
			try
			{
				sendeAenderungenAnAlle();
			}
			catch(RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else
		{
			String name = ((String[]) arg1)[0];
			String richtung = ((String[]) arg1)[1];
			try
			{
				_connectedClients.get(name).zeigeVorschau(
						_spiel.packeVorschauPaket(name, richtung));
			}
			catch(RemoteException e)
			{
				e.printStackTrace();
			}
		}

	}
}
