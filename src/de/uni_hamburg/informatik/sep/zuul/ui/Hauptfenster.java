package de.uni_hamburg.informatik.sep.zuul.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JPanel _ausgabePanel;
	private JPanel _eingabePanel;
	private JPanel _buttonPanel;

	/**
	 * Erzeuge ein neues Hauptfenster, welches die drei übergebenen panel
	 * darstellt.
	 * 
	 * @param ausgabePanel
	 * @param eingabePanel
	 * @param buttonPanel
	 */
	public Hauptfenster(JPanel ausgabePanel, JPanel eingabePanel,
			JPanel buttonPanel)
	{
		super();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_ausgabePanel = ausgabePanel;
		_eingabePanel = eingabePanel;
		_buttonPanel = buttonPanel;

		Container content = getContentPane();

		content.setLayout(new BorderLayout());

		GridBagConstraints c = new GridBagConstraints();
		Insets insets = new Insets(0, 0, 0, 0);
		c.insets = insets;

		c.gridx = 0;
		c.gridy = 0;
		content.add(_ausgabePanel, BorderLayout.NORTH);

		c.gridy = 1;
		content.add(_eingabePanel, BorderLayout.CENTER);

		c.gridy = 2;
		content.add(_buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
		setSize(1024, 920);
		setPreferredSize(new Dimension(1024, 920));
		setMinimumSize(new Dimension(1024, 920));
	}
}
