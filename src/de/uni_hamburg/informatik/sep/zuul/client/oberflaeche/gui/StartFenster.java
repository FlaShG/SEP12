package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.EditorStartup;
import de.uni_hamburg.informatik.sep.zuul.StartUp;
import de.uni_hamburg.informatik.sep.zuul.client.ClientGUI;
import de.uni_hamburg.informatik.sep.zuul.client.FileChooser;
import de.uni_hamburg.informatik.sep.zuul.editor.EditorFenster;
import de.uni_hamburg.informatik.sep.zuul.server.Server;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.SpielLogik;

public class StartFenster extends StartUp
{

	private StartfensterUI _ui;

	private String _ipAdresse;
	private int _port;
	private String _spielername;

	/**
	 * Erstellt ein neues Startfenster zum auswählen zwischen Single - und
	 * Multiplayer
	 */
	public StartFenster()
	{
		_ui = new StartfensterUI();

		_ipAdresse = "127.0.0.1";
		_port = 1090;

		_spielername = "Dr. Little";

		initialisiereUI();
	}

	private void initialisiereUI()
	{

		_ui.getSinglePlayerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dateiAuswaehlen(true);
				//starteRMI("RmiServer", "127.0.0.1", 1090, "Dr. Little", true);
			}
		});

		_ui.getMultiPlayerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ui.serverIPeingabe();
			}
		});

		_ui.getBestaetigen().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				_ui.getBestaetigen().setEnabled(false);
				_spielername = _ui.getSpielerNameTextField().getText();

				starteRMI("RmiServer", _ipAdresse, _port, _spielername, false);

			}

		});
		
		_ui.getEditorButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				EditorStartup.start();
				
				_ui.dispose();
			}
		});

		_ui.getAbbrechenButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		_ui.getIPTextField().addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				pruefeEingabe();
			}

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		});

		_ui.getPortTextField().addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				pruefeEingabe();
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub

			}
		});

		_ui.getServerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ui.levelWaehlen();
			}
		});

		_ui.getFileChooserButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dateiAuswaehlen(false);
			}

		});

		_ui.getDefaultMapButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				starteRMI("RmiServer", "localhost", 1090, "Dr.Little", true);
			}

		});

	}

	private void dateiAuswaehlen(final boolean modus)
	{

		Runnable run = new Runnable()
		{

			@Override
			public void run()
			{
				JFileChooser chooser;
				chooser = FileChooser.konfiguriereFileChooser(false);
				SpielLogik._levelPfad = FileChooser.oeffneDatei(chooser);
				String name;
				if(modus)
				{
					name = "Dr. Little";
				}
				else
				{
					name = "Dr.Little";
				}
				starteRMI("RmiServer", "localhost", 1090, name, true);
			}
		};

		EventQueue.invokeLater(run);
	}

	private void pruefeEingabe()
	{

		String eingabeIP = _ui.getIPTextField().getText();
		String eingabePort = _ui.getPortTextField().getText();

		if((eingabeIP
				.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") || eingabeIP
				.equals("localhost"))
				&& eingabePort.matches("109[0-9]"))
		{
			_ui.getBestaetigen().setEnabled(true);
			_ipAdresse = eingabeIP;
			_port = Integer.parseInt(eingabePort);
		}
		else
		{
			_ui.getBestaetigen().setEnabled(false);
		}

	}

	private void starteRMI(final String serverName, final String serverIP,
			final int port, final String clientName, final boolean serverStarten)
	{
		Runnable run = new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					if(serverStarten)
					{
						_server = new Server();
					}
					_client = new ClientGUI(serverName, serverIP, port,
							clientName);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				finally
				{
					_ui.dispose();
				}
			}
		};

		Thread rmiThread = new Thread(run, "ZuulRMIThread");
		rmiThread.start();
	}
}
