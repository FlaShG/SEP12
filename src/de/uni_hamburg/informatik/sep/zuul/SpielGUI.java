package de.uni_hamburg.informatik.sep.zuul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.AusgabePanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.ButtonPanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.EingabePanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.Hauptfenster;
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
			_bp.setRaumanzeige(raumbilderzeuger.getRaumansicht());
		}
	}

	private Hauptfenster _hf;
	private EingabePanel _ep;
	private AusgabePanel _ap;
	private ButtonPanel _bp;

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

				verarbeiteEingabe(str);

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

	}

	@Override
	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_ap.getAnzeigeArea().append("\n");
	}

	@Override
	public void schreibe(String nachricht)
	{
		JTextArea anzeige = _ap.getAnzeigeArea();

		anzeige.append(nachricht);
		anzeige.setCaretPosition(anzeige.getDocument().getLength());
	}

	@Override
	public void beendeSpiel()
	{

		_ep.getEingabeZeile().setEnabled(false);
		_ep.getEnterButton().setEnabled(false);

		_bp.getSouthButton().setEnabled(false);
		_bp.getNorthButton().setEnabled(false);
		_bp.getWestButton().setEnabled(false);
		_bp.getEastButton().setEnabled(false);
		_bp.getGibButton().setEnabled(false);
		_bp.getEssenButton().setEnabled(false);
		_bp.getEssenBodenButton().setEnabled(false);
		_bp.getNehmenButton().setEnabled(false);
		_bp.getHelpButton().setEnabled(false);
		_bp.getQuitButton().setEnabled(false);
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
		super.spielen(level);

		_kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(hasRoomChanged)
					zeichneBild();
				return true;
			}
		});
		zeichneBild();
	}

	private void zeichneBild()
	{
		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(_kontext);
		_bp.setRaumanzeige(raumbilderzeuger.getRaumansicht());
	}
}
