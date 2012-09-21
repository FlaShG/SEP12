package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.server.Server;

public class ClientConsole extends Client
{

	public ClientConsole(String serverName, String serverIP, Server server, String clientName)
	{
		super(serverName, serverIP, server, clientName);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
