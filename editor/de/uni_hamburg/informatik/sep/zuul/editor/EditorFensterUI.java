package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Die UI-Klasse des Editor-Fensters.
 * 
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
	 * 
	 * @param beobachter
	 *            ein Observer, der über alle Änderungen in der UI informiert
	 *            wird.
	 */
	public EditorFensterUI(EditorBeobachter beobachter)
	{
		_beobachter = beobachter;
	}

	/**
	 * (Re-)Initialisiert die UI.
	 * @param level ein Editorlevel für die levelglobalen Infos
	 * @param width die Breite der Karte
	 * @param height die Höhe der Karte
	 */
	public void init(EditorLevel level, int width, int height)
	{
		JFrame newFrame = new JFrame(EditorFenster.EDITOR_TITEL);

		newFrame.getContentPane().setLayout(new BorderLayout());

		JPanel north = new JPanel();
		FlowLayout northlayout = new FlowLayout();
		northlayout.setAlignment(FlowLayout.LEFT);
		north.setLayout(northlayout);
		newFrame.add(north, BorderLayout.NORTH);

		north.add(_menubar = new EditorMenuBar());
		north.add(_levelPanel = new LevelPanel(_beobachter, level));

		newFrame.add(_map = new EditorMap(width, height), BorderLayout.CENTER);
		_map.setBeobachter(_beobachter);

		_raumhinzu = new JButton("Raum anlegen");

		newFrame.setMinimumSize(new Dimension(900, 600));
		newFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		if(_frame != null)
		{
			newFrame.setLocation(_frame.getLocation());
			newFrame.setSize(_frame.getSize());
			newFrame.setExtendedState(_frame.getExtendedState()); 
			_frame.dispose();
		}
		else
		{
			newFrame.setLocationRelativeTo(SwingUtilities.getRoot(newFrame));
		}
		
		_frame = newFrame;
		
		_frame.setVisible(true);
	}

	/**
	 * Gibt die obere Menüleiste zurück.
	 * 
	 * @return
	 */
	public EditorMenuBar getMenuBar()
	{
		return _menubar;
	}

	/**
	 * Setzt die Map neu.
	 */
	public void setMap(EditorMap map)
	{
		_frame.remove(_map);
		_frame.add(_map = map, BorderLayout.CENTER);
		_map.setBeobachter(_beobachter);
		_frame.setVisible(true);
	}

	/**
	 * Gibt die Map (GridButton-Grid) zurück.
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
	 * Legt ein neues RaumBearbeitenPanel an, speichert die Referenz und gibt
	 * sie zurück.
	 * 
	 * @param raum
	 *            der Raum, der bearbeitet werden soll
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

	/**
	 * Gibt das Panel zurück, auf dem die levelglobalen Einstellungen getätigt werden.
	 * @return
	 */
	public LevelPanel getLevelPanel()
	{
		return _levelPanel;
	}
}
