package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.util.StringUtils;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlsPanel extends JPanel
{
	//TODO ANZAHLBUTTONS BEI NEUEN BEFEHLEN ANPASSEN!!!
	private final int ANZAHLBUTTONS = 10;
	private JButton _quitButton;
	private JButton _helpButton;
	private JButton _essenAusTascheButton;
	private JButton _nehmenButton;
	private JButton _gibButton;
	private JButton _essenBodenButton;
	private JButton _ladenButton;
	private JButton _fuettereButton;
	private JButton _ablegenButton;
	private JButton _inventarButton;

	private static final long serialVersionUID = 1L;

	public BefehlsPanel()
	{
		setLayout(null);

		_nehmenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_NEHMEN));

		_nehmenButton.setFocusable(false);

		_gibButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_GIB));

		_gibButton.setFocusable(false);

		_fuettereButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_FEED));

		_fuettereButton.setFocusable(false);

		_essenAusTascheButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ESSEN + " "
						+ TextVerwalter.ORT_TASCHE));
		_essenAusTascheButton.setFocusable(false);

		_essenBodenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ESSEN + " "
						+ TextVerwalter.ORT_BODEN));
		_essenBodenButton.setFocusable(false);

		_inventarButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_INVENTAR));
		_inventarButton.setFocusable(false);

		_ablegenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ABLEGEN));
		_ablegenButton.setFocusable(false);

		_helpButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_HILFE));
		_helpButton.setFocusable(false);

		_ladenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_LADEN));
		_ladenButton.setFocusable(false);

		_quitButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_BEENDEN));
		_quitButton.setFocusable(false);

		add(_nehmenButton);
		add(_gibButton);
		add(_fuettereButton);
		add(_essenAusTascheButton);
		add(_essenBodenButton);
		add(_inventarButton);
		add(_ablegenButton);
		add(_helpButton);
		add(_ladenButton);
		add(_quitButton);

		addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				int platzhalter = 5;

				int buttonheight = (BefehlsPanel.this.getHeight() - (platzhalter * 2 * (ANZAHLBUTTONS)))
						/ ANZAHLBUTTONS;

				Dimension buttongroesse = new Dimension(
						BefehlsPanel.this.getWidth() - 25, buttonheight);

				_nehmenButton.setSize(buttongroesse);
				_gibButton.setSize(buttongroesse);
				_fuettereButton.setSize(buttongroesse);
				_essenAusTascheButton.setSize(buttongroesse);
				_essenBodenButton.setSize(buttongroesse);
				_inventarButton.setSize(buttongroesse);
				_ablegenButton.setSize(buttongroesse);
				_helpButton.setSize(buttongroesse);
				_ladenButton.setSize(buttongroesse);
				_quitButton.setSize(buttongroesse);

				_nehmenButton.setLocation(5, 5);
				_gibButton.setLocation(5, buttonheight + 10);
				_fuettereButton.setLocation(5, buttonheight * 2 + 15);
				_essenAusTascheButton.setLocation(5, buttonheight * 3 + 20);
				_essenBodenButton.setLocation(5, buttonheight * 4 + 25);
				_inventarButton.setLocation(5, buttonheight * 5 + 30);
				_ablegenButton.setLocation(5, buttonheight * 6 + 35);
				_helpButton.setLocation(5, buttonheight * 7 + 40);
				_ladenButton.setLocation(5, buttonheight * 8 + 45);
				_quitButton.setLocation(5, buttonheight * 9 + 50);

			}

		});

	}

	public JButton getQuitButton()
	{
		return _quitButton;
	}

	public JButton getHelpButton()
	{
		return _helpButton;
	}

	public JButton getEssenAusTascheButton()
	{
		return _essenAusTascheButton;
	}

	public JButton getNehmenButton()
	{
		return _nehmenButton;
	}

	public JButton getGibButton()
	{
		return _gibButton;
	}

	public JButton getEssenBodenButton()
	{
		return _essenBodenButton;
	}

	public JButton getLadenButton()
	{
		return _ladenButton;
	}

	public JButton getFuettereButton()
	{
		return _fuettereButton;
	}

	public JButton getAblegenButton()
	{
		return _ablegenButton;
	}

	public JButton getInventarButton()
	{
		return _inventarButton;
	}

}
