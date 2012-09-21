package de.uni_hamburg.informatik.sep.zuul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.BefehlsPanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.BildPanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.KonsolenPanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.Startfenster;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raumbilderzeuger;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;
import de.uni_hamburg.informatik.sep.zuul.spiel.TickListener;

public class SpielGUI extends Spiel
{

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
			verarbeiteEingabe(_befehlszeile);
			Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(_kontext);
			_bildPanel.setRaumanzeige(raumbilderzeuger.getRaumansicht());
		}
	}

	private Hauptfenster _hf;
	private KonsolenPanel _kp;
	private BildPanel _bildPanel;
	private BefehlsPanel _bp;
	private Startfenster _sf;

	private String _ipadresse;
	private String _spielername;

	public SpielGUI()
	{
		super();
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
		_sf = new Startfenster();

		_hf.setVisible(false);
		_kp.getEnterButton().addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String str = _kp.getEingabeZeile().getText();
				_kp.getEingabeZeile().setText("");

				verarbeiteEingabe(str);

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

		_bildPanel.getSchaueNordButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN
						+ " " + TextVerwalter.RICHTUNG_NORDEN));

		_bildPanel.getSchaueOstButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN
						+ " " + TextVerwalter.RICHTUNG_OSTEN));

		_bildPanel.getSchaueSuedButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN
						+ " " + TextVerwalter.RICHTUNG_SUEDEN));

		_bildPanel.getSchaueWestButton().addActionListener(
				new ActionListenerBefehlAusfuehren(TextVerwalter.BEFEHL_SCHAUEN
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

	}

	@Override
	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_kp.getAnzeigeArea().append("\n");
	}

	@Override
	public void schreibe(String nachricht)
	{
		JTextArea anzeige = _kp.getAnzeigeArea();

		anzeige.append(nachricht);
		anzeige.setCaretPosition(anzeige.getDocument().getLength());
	}

	@Override
	public void beendeSpiel()
	{

		UIsetEnabled(false);
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
	protected void verarbeiteEingabe(String eingabezeile)
	{
		schreibeNL("> " + eingabezeile);
		super.verarbeiteEingabe(eingabezeile);
	}

	@Override
	public void spielen(String level)
	{
		UIsetEnabled(true);

		super.spielen(level);

		_kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				zeichneBild();
				return true;
			}
		});
		zeichneBild();
	}

	private void zeichneBild()
	{
		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(_kontext);
		_bildPanel.setRaumanzeige(raumbilderzeuger.getRaumansicht());
	}
}
