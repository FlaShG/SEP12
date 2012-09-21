package de.uni_hamburg.informatik.sep.zuul.multiplayer.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JTextArea;

import de.uni_hamburg.informatik.sep.zuul.multiplayer.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui.AusgabePanel;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui.ButtonPanel;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui.EingabePanel;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.client.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.multiplayer.server.util.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raumbilderzeuger;


public class ClientGUI extends Client
{

	private Hauptfenster _hf;
	private EingabePanel _ep;
	private AusgabePanel _ap;
	private ButtonPanel _bp;

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

		JTextArea anzeige = _ap.getAnzeigeArea();
		anzeige.append(text);
		anzeige.setCaretPosition(anzeige.getDocument().getLength());
	}

	@Override
	public boolean zeigeAn(ClientPaket paket)
	{

		schreibeText(paket.getNachricht());

		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(paket); //Spieler, items, maus, Katze anzeigen
		_bp.setRaumanzeige(raumbilderzeuger.getRaumansicht());

		//nötig??
		return true;

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
			try
			{
				verarbeiteEingabe(_befehlszeile);
			}
			catch(RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	void initialisiereUI()
	{
		_bp = new ButtonPanel(1024);
		_ep = new EingabePanel(1024);
		_ap = new AusgabePanel(1024);

		_hf = new Hauptfenster(_ap, _ep, _bp);

		_ep.getEnterButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String str = _ep.getEingabeZeile().getText();
				_ep.getEingabeZeile().setText("");

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

		_ep.getEingabeZeile().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ep.getEnterButton().doClick();
			}
		});

		_bp.getNorthButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_NORDEN));

		_bp.getSouthButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_SUEDEN));

		_bp.getEastButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_OSTEN));

		_bp.getWestButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_WESTEN));

		_bp.getTuerNordButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_NORDEN));

		_bp.getTuerOstButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_OSTEN));

		_bp.getTuerSuedButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_SUEDEN));
		_bp.getTuerWestButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_GEHEN
						+ " " + TextVerwalter.RICHTUNG_WESTEN));

		_bp.getQuitButton()
				.addActionListener(
						new ActionListenerBefehlAusfuehren(
								TextVerwalter.BEFEHL_BEENDEN));

		_bp.getHelpButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_HILFE));

		_bp.getEssenButton().addActionListener(
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

	}

	/**
	 * 
	 */
	private void UIsetEnabled(boolean value)
	{
		_ep.getEingabeZeile().setEnabled(value);
		_ep.getEnterButton().setEnabled(value);

		_bp.getSouthButton().setEnabled(value);
		_bp.getNorthButton().setEnabled(value);
		_bp.getWestButton().setEnabled(value);
		_bp.getEastButton().setEnabled(value);
		_bp.getGibButton().setEnabled(value);
		_bp.getEssenButton().setEnabled(value);
		_bp.getEssenBodenButton().setEnabled(value);
		_bp.getNehmenButton().setEnabled(value);
		_bp.getHelpButton().setEnabled(value);
		_bp.getQuitButton().setEnabled(value);
		_bp.getFuettereButton().setEnabled(value);
		_bp.getAblegenButton().setEnabled(value);
		_bp.getInventarButton().setEnabled(value);
	}

	public void schliesseFenster()
	{
		_hf.dispose();
	}

	@Override
	public void verarbeiteEingabe(String eingabezeile) throws RemoteException
	{
		super.verarbeiteEingabe(eingabezeile);
	}

	public void spielen(String level) throws RemoteException
	{
		UIsetEnabled(true);

		super.run();

	}

}
