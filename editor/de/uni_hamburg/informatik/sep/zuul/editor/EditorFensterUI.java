package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

/**
 * Die UI-Klasse des {@link EditorFenster}s.
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
	private JLabel _fueller;
	private RaumBearbeitenPanel _bearbeiten;
	private LevelPanel _levelPanel;

	private EditorBeobachter _beobachter;

	/**
	 * Erzeugt eine neue {@link EditorFensterUI}.
	 * 
	 * @param beobachter
	 *            ein {@link EditorBeobachter}, der über alle Änderungen in der
	 *            UI informiert wird.
	 */
	public EditorFensterUI(EditorBeobachter beobachter)
	{
		_beobachter = beobachter;
	}

	/**
	 * (Re-)Initialisiert die UI.
	 * 
	 * @param level
	 *            ein {@link EditorLevel} für die levelglobalen Infos
	 * @param width
	 *            die Breite der Karte
	 * @param height
	 *            die Höhe der Karte
	 * 
	 * @require width > 0
	 * @require heigth > 0
	 */
	public void init(EditorLevel level, int width, int height)
	{
		assert width > 0 : "Vorbedingung verletzt: width > 0";
		assert height > 0 : "Vorbedingung verletzt: height > 0";

		JFrame newFrame = new JFrame(TextVerwalter.EDITOR_TITEL);

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
		Dimension dim = new Dimension(500, 80);
		_raumhinzu.setSize(dim);
		_raumhinzu.setMinimumSize(dim);
		_raumhinzu.setPreferredSize(dim);

		newFrame.add(_fueller = new JLabel(
				EditorTextVerwalter.KLICKE_AUF_RAUM_ZUM_BEGINNEN),
				BorderLayout.SOUTH);
		_fueller.setSize(dim);
		_fueller.setMinimumSize(dim);
		_fueller.setPreferredSize(dim);
		_fueller.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
	 * Gibt die obere {@link EditorMenuBar} zurück.
	 * 
	 * @ensure result != null
	 */
	public EditorMenuBar getMenuBar()
	{
		return _menubar;
	}

	/**
	 * Setzt die Map neu.
	 * 
	 * @require map != null
	 */
	public void setMap(EditorMap map)
	{
		assert map != null : "Vorbedingung verletzt: map != null";

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
	 * Gibt den Füller für SOUTH zurück, wenn gerade nichts anderes drin ist.
	 */
	public JLabel getFueller()
	{
		return _fueller;
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
	 * Gibt das Panel zurück, auf dem die levelglobalen Einstellungen getätigt
	 * werden.
	 * 
	 * @return
	 */
	public LevelPanel getLevelPanel()
	{
		return _levelPanel;
	}
}
