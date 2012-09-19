package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

/**
 * Die UI-Klasse des Editor-Fensters.
 * @author 0graeff
 *
 */
public class EditorFensterUI implements Observer
{
	private JFrame _frame;
	
	private EditorMenuBar _menubar;
	private EditorMap _map;
	private JButton _raumhinzu;
	private RaumBearbeitenPanel _bearbeiten;
	private SpeicherWerkzeug _speicherWerkzeug;
	
	public EditorFensterUI()
	{
		_frame = new JFrame("Zuul-Editor");
		
		_frame.getContentPane().setLayout(new BorderLayout());
		
		
		_frame.add(_menubar = new EditorMenuBar(), BorderLayout.NORTH);
		_frame.add(_map = new EditorMap(8, 8), BorderLayout.CENTER);
		_map.setBeobachter(this);
		
		raumhinzuButtonAnlegen();
		
		//fancy größenwahn
		//_frame.pack();
		//_frame.setMinimumSize(_frame.getSize());
		//_frame.setSize(_frame.getSize().width, _frame.getSize().width);
		_frame.setMinimumSize(new Dimension(900, 600));
		
		
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);
		
		//--------------- 1griese
		
		_speicherWerkzeug = new SpeicherWerkzeug(_map);
		
		_menubar.getSpeicherButton().addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_speicherWerkzeug.speichern("./xml_dateien/speicherTest.xml");
			}
		});
		
		//----------------
		
	}
	
	private void raumhinzuButtonAnlegen()
	{
		_raumhinzu = new JButton("Raum anlegen");
		_raumhinzu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_map.fuegeRaumZuAktivemButtonHinzu();
				if(_map.getAktivenRaum() != null)
				{
					update(null, null);
				}
			}
		});
	}
	
	/**
	 * Gibt die obere Menüleiste zurück
	 * @return
	 */
	public EditorMenuBar getMenuBar()
	{
		return _menubar;
	}
	
	/**
	 * Gibt die Map (GridButton-Grid) zurück	
	 * @return
	 */
	public EditorMap getMap()
	{
		return _map;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		//DONT USE ARGS!!!
		
		_frame.remove(_raumhinzu);
		if(_bearbeiten != null)
			_frame.remove(_bearbeiten);
		_frame.setVisible(true);
		
		if(_map.buttonAusgewaehlt())
		{
			Raum raum = _map.getAktivenRaum();
			if(raum == null)
			{
				_frame.add(_raumhinzu, BorderLayout.SOUTH);
			}
			else
			{
				_frame.add(_bearbeiten = new RaumBearbeitenPanel(raum), BorderLayout.SOUTH);
			}
		}
		_frame.setVisible(true);
	}
}
