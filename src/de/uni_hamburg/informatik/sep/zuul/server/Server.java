package de.uni_hamburg.informatik.sep.zuul.server;

import java.rmi.RemoteException;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;

public class Server implements ServerInterface
{
	
	//Dummy
	
	@Override
	public boolean loginClient(ClientInterface client) throws RemoteException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logoutClient(ClientInterface client) throws RemoteException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean empfangeNutzerEingabe(String eingabe) throws RemoteException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
