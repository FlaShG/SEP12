package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Die Menülsiete, die die Buttons "neu", "laden", "speichern" und
 * "speichern unter" hält.
 * 
 * @author 0graeff
 * 
 */
public class EditorMenuBar extends JPanel
{
	private final JButton _new;
	private final JButton _save;
	private final JButton _load;
	private final JButton _resize;
	private final JButton _pathfinding;

	/**
	 * Erstellt eine neue {@link EditorMenuBar}.
	 */
	public EditorMenuBar()
	{
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);

		add(_new = new JButton("Neu"));
		add(_load = new JButton("Laden"));
		add(_save = new JButton("Speichern"));
		add(_resize = new JButton("Kartengröße"));
		add(_pathfinding = new JButton("Pathfinder"));
	}

	/**
	 * Gibt den "Neu"-Button zurück.
	 * 
	 * @ensure result != null
	 */
	public JButton getNeuButton()
	{
		return _new;
	}

	/**
	 * Gibt den "Speichern"-Button zurück.
	 * 
	 * @ensure result != null
	 */
	public JButton getSpeicherButton()
	{
		return _save;
	}

	/**
	 * Gibt den "Laden"-Button zurück.
	 * 
	 * @ensure result != null
	 */
	public JButton getLadenButton()
	{
		return _load;
	}

	/**
	 * Gibt den "Kartengröße"-Button zurück.
	 * 
	 * @ensure result != null
	 */
	public JButton getResizeButton()
	{
		return _resize;
	}
	
	/**
	 * Gibt den Pathfinding-Button zurück. 
	 * 
	 * @ensure result != null
	 */
	public JButton getPathfindingButton()
	{
		return _pathfinding;
	}
}
