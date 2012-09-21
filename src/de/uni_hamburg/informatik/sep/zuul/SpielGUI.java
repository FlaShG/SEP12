package de.uni_hamburg.informatik.sep.zuul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import sun.security.action.GetLongAction;

import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.BefehlsPanel;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.BildPanel;

import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.Hauptfenster;
import de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui.KonsolenPanel;
import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;
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
			if(_bildPanel.getWidth() > _bildPanel.getHeight())
				_bildPanel.setRaumanzeige(raumbilderzeuger
						.getRaumansicht(_bildPanel.getHeight()));
			else
				_bildPanel.setRaumanzeige(raumbilderzeuger
						.getRaumansicht(_bildPanel.getWidth()));
		}
	}

	private Hauptfenster _hf;
	private KonsolenPanel _kp;
	private BildPanel _bildPanel;
	private BefehlsPanel _bp;

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
					verarbeiteEingabe("schaue nord");
				}
				
			}
		});
		
		_bildPanel.getTuerOstButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				verarbeiteEingabe("schaue ost");
				
			}
		});
		
		_bildPanel.getTuerSuedButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				verarbeiteEingabe("schaue süd");
				
			}
		});
		
		_bildPanel.getTuerWestButton().addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				verarbeiteEingabe("schaue west");
				
			}
		});
		

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

		_kontext.addPropertyChangeListener("LebensEnergie",
				new PropertyChangeListener()
				{

					@Override
					public void propertyChange(PropertyChangeEvent p)
					{
						_bildPanel.setLebensenergie((int) p.getNewValue());
					}
				});
		
		_kontext.addPropertyChangeListener("AktuellerRaum", new PropertyChangeListener()
		{
			
			@Override
			public void propertyChange(PropertyChangeEvent p)
			{
				
				boolean n = false;
				boolean o = false;
				boolean s = false;
				boolean w = false;
				
				for(String richtung : ((Raum) p.getNewValue()).getMoeglicheAusgaenge())
				{
					
					
					if(richtung.equals("nord"))
						n = true;
					else if(richtung.equals("ost"))
						o = true;
					else if(richtung.equals("süd"))
						s = true;
					else if(richtung.equals("west"))
						w = true;
					
					_bildPanel.getTuerNordButton().setVisible(n);
					_bildPanel.getTuerOstButton().setVisible(o);
					_bildPanel.getTuerSuedButton().setVisible(s);
					_bildPanel.getTuerWestButton().setVisible(w);
				
				}
					
				
			}
		});

		_kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				if(_bildPanel.getWidth() > _bildPanel.getHeight())
					zeichneBild(_bildPanel.getLabelFuerIcon().getHeight());
				else
					zeichneBild(_bildPanel.getLabelFuerIcon().getWidth());
				return true;
			}
		});
		if(_bildPanel.getWidth() > _bildPanel.getHeight())
			zeichneBild(_bildPanel.getLabelFuerIcon().getHeight());
		else
			zeichneBild(_bildPanel.getLabelFuerIcon().getWidth());
	}

	private void zeichneBild(int breitehoehe)
	{
		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(_kontext);
		_bildPanel.setRaumanzeige(raumbilderzeuger.getRaumansicht(breitehoehe));
	}
}
