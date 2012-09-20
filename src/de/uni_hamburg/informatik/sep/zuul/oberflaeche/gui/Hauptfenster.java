package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Haupframe zum Vereinen (gemeinsamen Anzeigen) der einzelnen panels.
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public class Hauptfenster extends JFrame
{
	private AusgabePanel _ausgabePanel;
	private EingabePanel _eingabePanel;
	private JPanel _buttonPanel;

	/**
	 * Erzeuge ein neues Hauptfenster, welches die drei übergebenen panel
	 * darstellt.
	 * 
	 * @param ausgabePanel
	 * @param eingabePanel
	 * @param buttonPanel
	 */
	public Hauptfenster(AusgabePanel ausgabePanel, EingabePanel eingabePanel,
			JPanel buttonPanel)
	{
		super();

		setTitle("Zuul");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_ausgabePanel = ausgabePanel;
		_eingabePanel = eingabePanel;
		_buttonPanel = buttonPanel;

		Container content = getContentPane();

		content.setLayout(new BorderLayout());

		content.add(_ausgabePanel, BorderLayout.NORTH);

		content.add(_eingabePanel, BorderLayout.CENTER);

		content.add(_buttonPanel, BorderLayout.SOUTH);

		this.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent arg0)
			{
				_ausgabePanel.setGroesse(getSize().width,
						getHeight() - 440);
				_eingabePanel.setBreite(getSize().width);
			}
		});

		_eingabePanel.setDoubleBuffered(true);
		_ausgabePanel.setDoubleBuffered(true);
		_buttonPanel.setDoubleBuffered(true);

		setVisible(true);
		setSize(1024, 720);
		setPreferredSize(new Dimension(1024, 720));
		setMinimumSize(new Dimension(850, 500));
	}
}
