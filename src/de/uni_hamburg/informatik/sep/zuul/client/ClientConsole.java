package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class ClientConsole extends Client
{

	public ClientConsole(String serverName, String serverIP)
	{
		super(serverName, serverIP);
	}

	@Override
	public void run()
	{
		while(!_isSpielzuEnde)
		{
			String nachricht = leseZeileEin();

			sendeBefehl(nachricht);

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
	public void zeigeAn(ClientPaket raum) throws RemoteException
	{
		String nachricht = raum.getNachricht();
		System.out.println(nachricht);
	}

}
