package de.uni_hamburg.informatik.sep.zuul.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.junit.Before;
import org.junit.Test;

import com.sun.security.ntlm.Client;

import de.uni_hamburg.informatik.sep.zuul.client.ClientInterface;

public class ServerTest {

	Server _server;

	@Before
	public void setUp() throws Exception {
		_server = null;
		_server = new Server();

	}

	@Test
	public void testServer() throws RemoteException, AlreadyBoundException {
		assertNotNull(_server);
		_server.beendeServer();
	}

	@Test
	public void testBeendeServer() throws AccessException, RemoteException {
		_server.beendeServer();
		assertEquals(0, LocateRegistry.getRegistry(1099).list().length);
		// Registry muss nach abmelden leer sein.
	}

	@Test
	public void testSendeAenderungen() {
		_server.beendeServer();
	}

	@Test
	public void testLoginClient() throws RemoteException {
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");

		assertTrue(_server.getConnectedClients().containsValue(c1));
		assertTrue(_server.getConnectedClients().containsValue(c2));
		_server.beendeServer();
	}

	@Test
	public void testLogoutClient() throws RemoteException {
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
		assertFalse(_server.getConnectedClients().containsValue(c1));
		assertTrue(_server.getConnectedClients().containsValue(c2));

		_server.logoutClient("c2");
		assertFalse(_server.getConnectedClients().containsValue(c1));
		assertFalse(_server.getConnectedClients().containsValue(c2));
	}

	@Test
	public void testEmpfangeNutzerEingabe() throws RemoteException {

		// einloggen
		ClientInterface c1 = mock(ClientInterface.class);
		ClientInterface c2 = mock(ClientInterface.class);

		assertTrue(_server.getConnectedClients().isEmpty());

		_server.loginClient(c1, "c1");
		_server.loginClient(c2, "c2");
		
		assertTrue(_server.empfangeNutzerEingabe("eingabe", "c1"));
		verify(_server, atLeastOnce()).sendeAenderungen(anyList());
		
		

		_server.beendeServer();
	}

	@Test
	public void testEmpfangeStartEingabe() {
		_server.beendeServer();
	}

	@Test
	public void testGetConnectedClients() {
		_server.beendeServer();
	}

	@Test
	public void testUpdate() {
		_server.beendeServer();
	}

	protected void tearDown() {
		_server.beendeServer();
	}

}
