package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientConsole extends Client
{

	public ClientConsole(String serverName, String serverIP, int clientport,
			String clientName) throws MalformedURLException, RemoteException,
			NotBoundException
	{
		super(serverName, serverIP, clientport, clientName);
	}

	@Override
	public void schreibeText(String text)
	{
		System.out.println(text);
	}

	@Override
	public void run() throws RemoteException
	{
		while(!_isSpielzuEnde)
		{
			String nachricht = leseZeileEin();

			verarbeiteEingabe(nachricht);

			//TODO warten einbauen
		}
	}

	/**
	 * @return
	 */
	protected String leseZeileEin()
	{
		System.out.print("> ");

		String eingabezeile = "";
		BufferedReader eingabe = new BufferedReader(new InputStreamReader(
				System.in));
		try
		{
			eingabezeile = eingabe.readLine();
		}
		catch(IOException exc)
		{
			System.out.println("There was an error during reading: "
					+ exc.getMessage());
		}
		return eingabezeile;
	}

	@Override
	public boolean zeigeAn(ClientPaket paket) throws RemoteException
	{
		System.out.println(paket.getNachricht());
		return true;
	}

}
