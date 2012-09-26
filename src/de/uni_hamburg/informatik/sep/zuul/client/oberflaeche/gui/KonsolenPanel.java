package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.sun.xml.internal.ws.util.StringUtils;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class KonsolenPanel extends JPanel
{
	private JPanel _eingabePanel;
	private JPanel _ausgabePanel;

	//eingabe
	private JTextField _eingabeZeile;
	private JButton _enterButton;

	//ausgabe
	private JTextArea _anzeige;
	private JScrollPane _scrollPane;

	public KonsolenPanel()
	{

		setLayout(null);
		setSize(1024, 512);

		//eingabe
		_eingabeZeile = new JTextField();
		_eingabeZeile.setLocation(0, 0);

		_enterButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BUTTON_EINGEBEN));
		_enterButton.setFocusable(false);
		_enterButton.setVisible(true);
		_enterButton.setSize(100, 30);
		add(_eingabeZeile);
		add(_enterButton);

		//ausgabe

		_anzeige = new JTextArea();
		_anzeige.setEditable(false);
		_anzeige.setLineWrap(true);
		_anzeige.setWrapStyleWord(true);
		_anzeige.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		_scrollPane = new JScrollPane();
		_scrollPane.setViewportView(_anzeige);
		_scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(_scrollPane);

		addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent arg0)
			{

				_eingabeZeile.setSize(KonsolenPanel.this.getWidth() - 100, 30);
				_eingabeZeile.setLocation(0,
						KonsolenPanel.this.getHeight() - 70);

				_enterButton.setLocation(_eingabeZeile.getWidth(),
						KonsolenPanel.this.getHeight() - 70);

				_scrollPane.setSize(KonsolenPanel.this.getWidth(),
						KonsolenPanel.this.getHeight() - 70);

				_anzeige.setSize(_scrollPane.getWidth(),
						_scrollPane.getHeight());
				_anzeige.setLocation(0, 0);

			}
		});

		initialisiereUI();
	}

	private void initialisiereUI()
	{

	}

	public JButton getEnterButton()
	{
		return _enterButton;
	}

	public JTextField getEingabeZeile()
	{
		return _eingabeZeile;
	}

	public JTextArea getAnzeigeArea()
	{
		return _anzeige;
	}

}
