package de.uni_hamburg.informatik.sep.zuul.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTextArea;

import de.uni_hamburg.informatik.sep.zuul.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.BefehlsPanel;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.BildPanel;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui.KonsolenPanel;
import de.uni_hamburg.informatik.sep.zuul.server.Server;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raumbilderzeuger;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class ClientGUI extends Client
{

	@Override
	public void schreibeText(String text)
	{

		JTextArea anzeige = _kp.getAnzeigeArea();
		anzeige.append(text);
		anzeige.setCaretPosition(anzeige.getDocument().getLength());
	}

	@Override
	public boolean zeigeAn(ClientPaket paket)
	{

		schreibeText(paket.getNachricht());

		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(paket); //Spieler, items, maus, Katze anzeigen
		_bildPanel.setRaumanzeige(raumbilderzeuger.getRaumansicht());
		
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


	private Hauptfenster _hf;
	private KonsolenPanel _kp;
	private BildPanel _bildPanel;
	private BefehlsPanel _bp;
	
	public ClientGUI(String serverName, String serverIP, Server server, String clientName)
	{
		super(serverName, serverIP, server, clientName);
		initialisiereUI();

	}

	/**
	 * 
	 */
	void initialisiereUI()
	{

		_bp = new BefehlsPanel();
		_kp = new KonsolenPanel();
		_bildPanel = new BildPanel();
		_hf = new Hauptfenster(_bildPanel, _kp, _bp);

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

		_bildPanel.getSchaueNordButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN + " "
						+ TextVerwalter.RICHTUNG_NORDEN));

		_bildPanel.getSchaueOstButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN + " "
						+ TextVerwalter.RICHTUNG_OSTEN));

		_bildPanel.getSchaueSuedButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN + " "
						+ TextVerwalter.RICHTUNG_SUEDEN));

		_bildPanel.getSchaueWestButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN + " "
						+ TextVerwalter.RICHTUNG_WESTEN));

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
		_bildPanel.getSchaueNordButton().setEnabled(value);
		_bildPanel.getSchaueOstButton().setEnabled(value);
		_bildPanel.getSchaueSuedButton().setEnabled(value);
		_bildPanel.getSchaueWestButton().setEnabled(value);
	}

	public void schliesseFenster()
	{
		_hf.hide();
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
