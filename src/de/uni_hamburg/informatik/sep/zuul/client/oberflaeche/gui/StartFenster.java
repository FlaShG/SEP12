package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingWorker;

import de.uni_hamburg.informatik.sep.zuul.client.ClientGUI;

public class StartFenster
{

	private StartfensterUI _ui;

	private String _ipAdresse;
	private String _spielername;

	public StartFenster()
	{
		_ui = new StartfensterUI();
		
		_ipAdresse = "127.0.0.1";

		initialisiereUI();
	}

	private void initialisiereUI()
	{
		_ui.getSinglePlayerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					new ClientGUI("RmiServer", "127.0.0.1", 1090, "Dr. Little");
				}
				catch(MalformedURLException | RemoteException
						| NotBoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				_ui.dispose();
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

		_ui.getIPTextField().addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				//  Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					pruefeIP();
					_spielername = _ui.getSpielerNameTextField().getText();
					
					try
					{
						new ClientGUI("RmiServer", _ipAdresse, 1090, _spielername);
					}
					catch(MalformedURLException | RemoteException
							| NotBoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			private void pruefeIP()
			{
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
				{

					@Override
					protected Void doInBackground() throws Exception
					{
						String[] tokens = _ui.getIPTextField().getText()
								.split("\\.");
						if(tokens.length == 4)
						{
							for(String str : tokens)
							{
								int i = Integer.parseInt(str);
								if(!((i < 0) || (i > 255)))
								{
									_ipAdresse = _ui.getIPTextField().getText();
								}
							}
						}
						return null;
					}
				};

				worker.execute();
			}

		});
	}

}
