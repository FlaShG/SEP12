package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;

/**
 * Das Panel, das das Einstellen aller Eigenschaften eines Raumes ermöglicht.
 * Inklusive Löschen-Button.
 * 
 * @author 0graeff
 */
public class RaumBearbeitenPanel extends JPanel
{
	private JTextArea _beschreibung;
	private RaumEigenschaftenPanel _eigenschaften;
	private JButton _loeschen;

	private final Raum _raum;

	/**
	 * Erzeugt ein {@link RaumBearbeitenPanel} für einen bestimmten {@link Raum}.
	 */
	public RaumBearbeitenPanel(Raum raum, final EditorBeobachter beobachter)
	{
		_raum = raum;

		setLayout(new BorderLayout());

		_beschreibung = new JTextArea();
		
		//beschreibung in ein ScrollPane tun
		_beschreibung.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		Dimension dim = new Dimension(460, 80);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(_beschreibung);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(dim);
		scrollPane.setPreferredSize(dim);
		scrollPane.setMinimumSize(dim);
		add(scrollPane, BorderLayout.WEST);
		
		_beschreibung.setLineWrap(true);
		_beschreibung.setWrapStyleWord(true);
		_beschreibung.setText(_raum.getBeschreibung());
		_beschreibung.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				beobachter.eigenschaftUpdate();
				_raum.setBescheibung(_beschreibung.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				beobachter.eigenschaftUpdate();
				_raum.setBescheibung(_beschreibung.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
			}
		});

		add(_eigenschaften = new RaumEigenschaftenPanel(raum, beobachter),
				BorderLayout.CENTER);

		add(_loeschen = new JButton("löschen"), BorderLayout.EAST);
	}

	/**
	 * Gibt den bearbeiteten {@link Raum} zurück.
	 */
	public Raum getRaum()
	{
		return _raum;
	}

	/**
	 * Gibt den Löschen-Button des Panels zurück.
	 */
	public JButton getLoeschenButton()
	{
		return _loeschen;
	}
	
	/**
	 * Gibt das Raumbeschreibungs-TextArea zurück.
	 */
	public JTextArea getBeschreibung()
	{
		return _beschreibung;
	}

	/**
	 * Gibt das {@link RaumEigenschaftenPanel} zurück.
	 */
	public RaumEigenschaftenPanel getEigenschaftenPanel()
	{
		return _eigenschaften;
	}
}
