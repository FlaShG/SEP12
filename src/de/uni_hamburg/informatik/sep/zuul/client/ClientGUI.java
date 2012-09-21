package de.uni_hamburg.informatik.sep.zuul.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.BefehlsPanel;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.BildPanel;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.KonsolenPanel;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.Startfenster;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;


public class ClientGUI extends Client
{

	public ClientGUI(String serverName, String serverIP, int clientport,
			String clientName) throws MalformedURLException, RemoteException,
			NotBoundException
	{
		super(serverName, serverIP, clientport, clientName);
		initialisiereUI();

	}

	@Override
	public void schreibeText(String text)
	{

		JTextArea anzeige = _kp.getAnzeigeArea();
		anzeige.append(text);
		anzeige.setCaretPosition(anzeige.getDocument().getLength());
	}

	@Override
	public synchronized boolean zeigeAn(final ClientPaket paket)
	{
		
		SwingUtilities.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				aktualisiereUI(paket);
			}
		});

		//nötig??
		return true;

	}

	/**
	 * @param paket
	 */
	private void aktualisiereUI(ClientPaket paket)
	{
		schreibeText(paket.getNachricht());

		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(paket); //Spieler, items, maus, Katze anzeigen
		// TODO: falsch?
		if(_bildPanel.getWidth() > _bildPanel.getHeight())
			_bildPanel.setRaumanzeige(raumbilderzeuger
					.getRaumansicht(_bildPanel.getHeight()));
		else
			_bildPanel.setRaumanzeige(raumbilderzeuger
					.getRaumansicht(_bildPanel.getWidth()));
	}

	private final class ActionListenerBefehlAusfuehren implements
			ActionListener
	{
		private String _befehlszeile;

		public ActionListenerBefehlAusfuehren(String befehlszeile)
		{
			_befehlszeile = befehlszeile;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			sendeEingabe(_befehlszeile);
		}
	}

	private Hauptfenster _hf;
	private KonsolenPanel _kp;
	private BildPanel _bildPanel;
	private BefehlsPanel _bp;
	private Startfenster _sf;

	private String _ipadresse;
	private String _spielername;

	/**
	 * 
	 */
	void initialisiereUI()
	{

		_bp = new BefehlsPanel();
		_kp = new KonsolenPanel();
		_bildPanel = new BildPanel();
		_hf = new Hauptfenster(_bildPanel, _kp, _bp);
		_sf = new Startfenster();

		_hf.setVisible(false);
		_kp.getEnterButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String str = _kp.getEingabeZeile().getText();
				_kp.getEingabeZeile().setText("");

				try
				{
					verarbeiteEingabe(str);
				}
				catch(RemoteException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		_sf.getSinglePlayerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_hf.setVisible(true);
				_sf.setVisible(false);
				_sf.dispose();
			}
		});

		_sf.getMultiPlayerButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_sf.serverIPeingabe();
			}
		});

		_sf.getIPTextField().addKeyListener(new KeyListener()
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
					_spielername = _sf.getSpielerNameTextField().getText();
				}

			}

			private void pruefeIP()
			{
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
				{

					@Override
					protected Void doInBackground() throws Exception
					{
						String[] tokens = _sf.getIPTextField().getText()
								.split("\\.");
						if(tokens.length == 4)
						{
							for(String str : tokens)
							{
								int i = Integer.parseInt(str);
								if(!((i < 0) || (i > 255)))
								{
									_ipadresse = _sf.getIPTextField().getText();
								}
							}
						}
						return null;
					}
				};

				worker.execute();
			}

		});

		_kp.getEingabeZeile().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_kp.getEnterButton().doClick();
			}
		});

		_bildPanel.getTuerNordButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_NORDEN));

		_bildPanel.getTuerOstButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_OSTEN));

		_bildPanel.getTuerSuedButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_SUEDEN));
		_bildPanel.getTuerWestButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_WESTEN));

		_bp.getQuitButton()
				.addActionListener(
						new ActionListenerBefehlAusfuehren(
								TextVerwalter.BEFEHL_BEENDEN));

		_bp.getHelpButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_HILFE));

		_bp.getEssenAusTascheButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_ESSEN
						+ " " + TextVerwalter.ORT_TASCHE));

		_bp.getEssenBodenButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_ESSEN
						+ " " + TextVerwalter.ORT_BODEN));

		_bp.getNehmenButton()
				.addActionListener(
						new ActionListenerBefehlAusfuehren(
								TextVerwalter.BEFEHL_NEHMEN));

		_bp.getGibButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GIB));

		_bp.getLadenButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_LADEN));

		_bp.getFuettereButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_FEED));

		_bp.getInventarButton().addActionListener(
				new ActionListenerBefehlAusfuehren(
						TextVerwalter.BEFEHL_INVENTAR));

		_bp.getAblegenButton()
				.addActionListener(
						new ActionListenerBefehlAusfuehren(
								TextVerwalter.BEFEHL_ABLEGEN));

		_bildPanel.addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				// TODO: bild neuzeichen ohne client paket
				if(_bildPanel.getWidth() > _bildPanel.getHeight())
					zeichneBild(_bildPanel.getLabelFuerIcon().getHeight());
				else
					zeichneBild(_bildPanel.getLabelFuerIcon().getWidth());
			}

		});

		_bildPanel.getTuerNordButton().addMouseListener(new MouseAdapter()
		{
			
			
			@Override
			public void mouseClicked(MouseEvent m)
			{
				if(SwingUtilities.isRightMouseButton(m) && m.getClickCount() == 1)
				{
					sendeEingabe("schaue nord");
				}
				
			}
		});
		
		_bildPanel.getTuerOstButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				sendeEingabe("schaue ost");
				
			}
		});
		
		_bildPanel.getTuerSuedButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				sendeEingabe("schaue süd");
				
			}
		});
		
		_bildPanel.getTuerWestButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				sendeEingabe("schaue west");
				
			}
		});
		

	}

	/**
	 * 
	 */
	private void UIsetEnabled(boolean value)
	{
		_kp.getEingabeZeile().setEnabled(value);
		_kp.getEnterButton().setEnabled(value);
		_bp.getGibButton().setEnabled(value);
		_bp.getEssenAusTascheButton().setEnabled(value);
		_bp.getEssenBodenButton().setEnabled(value);
		_bp.getNehmenButton().setEnabled(value);
		_bp.getHelpButton().setEnabled(value);
		_bp.getQuitButton().setEnabled(value);
		_bp.getFuettereButton().setEnabled(value);
		_bp.getAblegenButton().setEnabled(value);
		_bp.getInventarButton().setEnabled(value);
		_bildPanel.getTuerNordButton().setEnabled(value);
		_bildPanel.getTuerOstButton().setEnabled(value);
		_bildPanel.getTuerSuedButton().setEnabled(value);
		_bildPanel.getTuerWestButton().setEnabled(value);
		
	}

	public void schliesseFenster()
	{
		_hf.dispose();
	}

	public void spielen(String level) throws RemoteException
	{
		UIsetEnabled(true);

		super.run();

// TODO: move; geht client paket oder so ähnlich.
//		_kontext.addPropertyChangeListener("LebensEnergie",
//				new PropertyChangeListener()
//				{
//
//					@Override
//					public void propertyChange(PropertyChangeEvent p)
//					{
//						_bildPanel.setLebensenergie((int) p.getNewValue());
//					}
//				});
//		
//		_kontext.addPropertyChangeListener("AktuellerRaum", new PropertyChangeListener()
//		{
//			
//			@Override
//			public void propertyChange(PropertyChangeEvent p)
//			{
//				
//				boolean n = false;
//				boolean o = false;
//				boolean s = false;
//				boolean w = false;
//				
//				for(String richtung : ((Raum) p.getNewValue()).getMoeglicheAusgaenge())
//				{
//					
//					
//					if(richtung.equals("nord"))
//						n = true;
//					else if(richtung.equals("ost"))
//						o = true;
//					else if(richtung.equals("süd"))
//						s = true;
//					else if(richtung.equals("west"))
//						w = true;
//					
//					_bildPanel.getTuerNordButton().setVisible(n);
//					_bildPanel.getTuerOstButton().setVisible(o);
//					_bildPanel.getTuerSuedButton().setVisible(s);
//					_bildPanel.getTuerWestButton().setVisible(w);
//				
//				}
//					
//				
//			}
//		});
//
//		_kontext.addTickListener(new TickListener()
//		{
//
//			@Override
//			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
//			{
//				if(_bildPanel.getWidth() > _bildPanel.getHeight())
//					zeichneBild(_bildPanel.getLabelFuerIcon().getHeight());
//				else
//					zeichneBild(_bildPanel.getLabelFuerIcon().getWidth());
//				return true;
//			}
//		});
//		if(_bildPanel.getWidth() > _bildPanel.getHeight())
//			zeichneBild(_bildPanel.getLabelFuerIcon().getHeight());
//		else
//			zeichneBild(_bildPanel.getLabelFuerIcon().getWidth());
	}

	private void zeichneBild(ClientPaket paket, int breitehoehe)
	{
		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(paket);
		_bildPanel.setRaumanzeige(raumbilderzeuger.getRaumansicht(breitehoehe));
	}


	private void zeichneBild(int height)
	{
		// TODO Auto-generated method stub
		// TODO: Bild ohne neues client paket neu zeichen
		
	}

	/**
	 * @param befehlszeile 
	 * 
	 */
	private void sendeEingabe(String befehlszeile)
	{
		try
		{
			verarbeiteEingabe(befehlszeile);
		}
		catch(RemoteException e1)
		{
			// TODO Auto-generated catch block
			// TODO: exception verarbeiten
			e1.printStackTrace();
		}
	}

}