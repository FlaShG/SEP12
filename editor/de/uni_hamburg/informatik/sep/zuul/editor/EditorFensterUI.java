package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

/**
 * Die UI-Klasse des Editor-Fensters.
 * @author 0graeff
 *
 */
public class EditorFensterUI
{
	private JFrame _frame;
	
	private EditorMenuBar _menubar;
	private EditorMap _map;
	private JButton _raumhinzu;
	private RaumBearbeitenPanel _bearbeiten;
	private LevelPanel _levelPanel;
	
	private EditorBeobachter _beobachter;
	
	/**
	 * Erzeugt eine neue EditorFensterUI.
	 * @param beobachter Ein Observer, der über alle Änderungen in der UI informiert wird.
	 * @param level 
	 */
	public EditorFensterUI(EditorBeobachter beobachter, EditorLevel level)
	{
		_beobachter = beobachter;
		
		
		_frame = new JFrame("Zuul-Editor");
		
		_frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel north = new JPanel();
		FlowLayout northlayout = new FlowLayout();
		northlayout.setAlignment(FlowLayout.LEFT);
		north.setLayout(northlayout);
		_frame.add(north, BorderLayout.NORTH);
		
		north.add(_menubar = new EditorMenuBar());
		north.add(_levelPanel = new LevelPanel(beobachter, level));
		
		_frame.add(_map = new EditorMap(8, 8), BorderLayout.CENTER);
		_map.setBeobachter(beobachter);
		
		_raumhinzu = new JButton("Raum anlegen");
	
		
		//fancy größenwahn
		//_frame.pack();
		//_frame.setMinimumSize(_frame.getSize());
		//_frame.setSize(_frame.getSize().width, _frame.getSize().width);
		_frame.setMinimumSize(new Dimension(900, 600));
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);		
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
	 * Setzt die Map
	 * @return
	 */
	public void setMap(EditorMap map)
	{
		_frame.remove(_map);
		_frame.add(_map = map, BorderLayout.CENTER);
		_map.setBeobachter(_beobachter);
		_frame.setVisible(true);
	}
	
	/**
	 * Gibt die Map (GridButton-Grid) zurück	
	 * @return
	 */
	public EditorMap getMap()
	{
		return _map;
	}
	
	/**
	 * Gibt den Raum-hinzu-Button zurück
	 */
	public JButton getRaumhinzu()
	{
		return _raumhinzu;
	}
	
	/**
	 * Gibt das RaumBearbeitenPanel zurück
	 */
	public RaumBearbeitenPanel getBearbeitenPanel()
	{
		return _bearbeiten;
	}
	
	/**
	 * Gibt den JFrame zurück, den diese Klasse erstellt 
	 */
	public JFrame getFrame()
	{
		return _frame;
	}

	/**
	 * Legt ein neues RaumBearbeitenPanel an, speichert die Referenz und gibt sie zurück.
	 * @param raum der Raum, der bearbeitet werden soll
	 * @return Das neue RaumBearbeitenPanel
	 */
	public RaumBearbeitenPanel neuesBearbeitenPanel(Raum raum)
	{
		 return _bearbeiten = new RaumBearbeitenPanel(raum, _beobachter);
	}

	/**
	 * Entfernt die Referenz auf das aktuelle Bearbeiten-Panel
	 */
	public void loescheBearbeitenPanel()
	{
		_bearbeiten = null;
	}

	public LevelPanel getLevelPanel()
	{
		return _levelPanel;
	}
}
