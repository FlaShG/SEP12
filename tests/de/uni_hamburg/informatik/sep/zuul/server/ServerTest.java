package de.uni_hamburg.informatik.sep.zuul.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;
import de.uni_hamburg.informatik.sep.zuul.client.ClientPaket;

public class ServerTest
{

	Server _server;

	@Before
	public void setUp() throws Exception
	{
		_server = null;
		_server = new Server();

	}

	@Test
	public void testServer() throws RemoteException, AlreadyBoundException
	{
		assertNotNull(_server);
	}

	@Test
	public void testLoginClient() throws RemoteException
	{
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		assertTrue(_server.getConnectedClients().containsValue(c1));
		assertTrue(_server.getConnectedClients().containsValue(c2));
		//		_server.beendeServer();
	}

	@Test
	public void testLogoutClient() throws RemoteException, InterruptedException
	{
		// einloggen
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		assertTrue(_server.getConnectedClients().containsValue(c1));
		assertTrue(_server.getConnectedClients().containsValue(c2));

		// ausloggen
		_server.logoutClient("c1");

		Thread.sleep(2000);
		assertFalse(_server.getConnectedClients().containsValue(c1));
		assertTrue(_server.getConnectedClients().containsValue(c2));

		_server.logoutClient("c2");
		Thread.sleep(2000);
		assertFalse(_server.getConnectedClients().containsValue(c1));
		assertFalse(_server.getConnectedClients().containsValue(c2));

		//		_server.beendeServer();
	}

	@Test
	public void testEmpfangeNutzerEingabe() throws RemoteException,
			InterruptedException
	{

		// einloggen
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		assertTrue(_server.empfangeNutzerEingabe("gehe nord", "c1"));
		assertTrue(_server.empfangeNutzerEingabe("eingabe", "c2"));
		Thread.sleep(1000);
		verify(c1, atLeastOnce()).zeigeAn(any(ClientPaket.class));
		verify(c2, atLeastOnce()).zeigeAn(any(ClientPaket.class));

		//		_server.beendeServer();
	}

	@Test
	public void testEmpfangeStartEingabe() throws RemoteException,
			InterruptedException
	{
		// nur ein spieler loggt ein und startet:
		ClientInterface c1 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.empfangeStartEingabe("c1");
		Thread.sleep(1000);
		verify(c1, atLeastOnce()).zeigeAn(any(ClientPaket.class));

		_server.beendeServer();
	}

	@Test
	public void testGetConnectedClients() throws RemoteException
	{

		// einloggen
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		assertTrue(_server.getConnectedClients().values().contains(c1));
		assertTrue(_server.getConnectedClients().values().contains(c2));

		//		_server.beendeServer();
	}

	@Test
	public void testUpdate() throws RemoteException
	{
		// einloggen
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		_server.update(null, null);
		verify(c1, atLeastOnce()).zeigeAn(any(ClientPaket.class));
		verify(c2, atLeastOnce()).zeigeAn(any(ClientPaket.class));

		String[] array = { "c1", "nord" };
		_server.update(null, array);
		verify(c1, atLeastOnce()).zeigeVorschau(any(ClientPaket.class));

		//		_server.beendeServer();
	}

	@After
	public void tearDown() throws Exception
	{
		Thread.sleep(1000);
		if(LocateRegistry.getRegistry(1099).lookup("RmiServer") != null)
		{
			_server.beendeServer();
		}
		assertEquals(0, LocateRegistry.getRegistry(1099).list().length);
	}
}
