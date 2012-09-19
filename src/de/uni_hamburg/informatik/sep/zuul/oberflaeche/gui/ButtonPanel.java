package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import sun.security.util.SecurityConstants.AWT;

import com.sun.xml.internal.ws.util.StringUtils;

import de.uni_hamburg.informatik.sep.zuul.spiel.TextVerwalter;

public class ButtonPanel extends JPanel
{
	private JButton _northButton;
	private JButton _southButton;
	private JButton _eastButton;
	private JButton _westButton;

	private JButton _quitButton;
	private JButton _helpButton;

	private JLabel _platzhalter;
	private JLabel _LabelFuerIcon;

	private final Dimension BUTTONGROESSE = new Dimension(100, 45);
	private final Dimension PREFERRED = new Dimension(120, 25);
	private final int HOEHE = 250;
	private final int SCHRIFTGROESSE = 12;
	private JButton _essenAusTascheButton;
	private JButton _nehmenButton;
	private JButton _gibButton;
	private JButton _essenBodenButton;
	private JButton _ladenButton;
	private ImageIcon _raumIcon;

	public ButtonPanel(int breite)
	{
		super(new GridBagLayout());
		setSize(breite, HOEHE);
		setPreferredSize(new Dimension(breite, HOEHE));
		
		Insets mehrPlatz = new Insets(0,0,0,0);
		Font font = new Font("Dialog", Font.PLAIN, SCHRIFTGROESSE);
		UIManager.put("Button.font", font);

		_platzhalter = new JLabel(
				"                                                 ");
		_platzhalter.setVisible(true);

		ImageIcon north = new ImageIcon(getClass().getResource(
				"bilder/north.png"));
		ImageIcon south = new ImageIcon(getClass().getResource(
				"bilder/south.png"));
		ImageIcon west = new ImageIcon(getClass()
				.getResource("bilder/west.png"));
		ImageIcon east = new ImageIcon(getClass()
				.getResource("bilder/east.png"));

		//Buttons initialisieren:
		_northButton = new JButton(
				StringUtils.capitalize(TextVerwalter.RICHTUNG_NORDEN), north);
		_northButton.setPreferredSize(BUTTONGROESSE);
		_northButton.setFocusable(false);
		_northButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
		_northButton.setMargin(mehrPlatz);
		_northButton.setIconTextGap(0);
		
		_southButton = new JButton(
				StringUtils.capitalize(TextVerwalter.RICHTUNG_SUEDEN), south);
		_southButton.setFocusable(false);
		_southButton.setPreferredSize(BUTTONGROESSE);
		_southButton.setMargin(mehrPlatz);
		_southButton.setIconTextGap(0);
		
		_westButton = new JButton(
				StringUtils.capitalize(TextVerwalter.RICHTUNG_WESTEN), west);
		_westButton.setFocusable(false);
		_westButton.setPreferredSize(BUTTONGROESSE);
		_westButton.setMargin(mehrPlatz);
		_westButton.setIconTextGap(0);
		
		_eastButton = new JButton(
				StringUtils.capitalize(TextVerwalter.RICHTUNG_OSTEN), east);
		_eastButton.setPreferredSize(BUTTONGROESSE);
		_eastButton.setFocusable(false);
		_eastButton.setMargin(mehrPlatz);
		_eastButton.setIconTextGap(0);

		// befehlbuttons
		_quitButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_BEENDEN));
		_quitButton.setMinimumSize(PREFERRED);
		_quitButton.setPreferredSize(PREFERRED);
		_quitButton.setFocusable(false);

		_helpButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_HILFE));
		_helpButton.setMinimumSize(PREFERRED);
		_helpButton.setPreferredSize(PREFERRED);
		_helpButton.setFocusable(false);

		_gibButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_GIB));
		_gibButton.setMinimumSize(PREFERRED);
		_gibButton.setPreferredSize(PREFERRED);
		_gibButton.setFocusable(false);

		_essenAusTascheButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ESSEN + " "
						+ TextVerwalter.ORT_TASCHE));
		_essenAusTascheButton.setMinimumSize(PREFERRED);
		_essenAusTascheButton.setPreferredSize(PREFERRED);
		_essenAusTascheButton.setFocusable(false);

		_essenBodenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ESSEN + " "
						+ TextVerwalter.ORT_BODEN));
		_essenBodenButton.setMinimumSize(PREFERRED);
		_essenBodenButton.setPreferredSize(PREFERRED);
		_essenBodenButton.setFocusable(false);

		_nehmenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_NEHMEN));
		_nehmenButton.setMinimumSize(PREFERRED);
		_nehmenButton.setPreferredSize(PREFERRED);
		_nehmenButton.setFocusable(false);

		_ladenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_LADEN));
		_ladenButton.setMinimumSize(PREFERRED);
		_ladenButton.setFocusable(false);

		//Raumanzeige initialisieren
		_raumIcon = new ImageIcon("Z:\\SEP\\test.png");

		_LabelFuerIcon = new JLabel(_raumIcon);
		_LabelFuerIcon.setMinimumSize(new Dimension(245, 245));

		initialisiereUI();

	}

	/**
	 * Belege die UI mit Standardwerten und ordne die Elemente in einem
	 * Gridbaglayout an. Es werden dabei zwei neue Panels erstellt :
	 * steuerungsPanel und befehlsPanel mit jeweils eigenem GridBagLayout
	 */
	private void initialisiereUI()
	{

		JPanel steuerungsPanel = new JPanel();
		JPanel befehlsPanel = new JPanel();
		JPanel raumPanel = new JPanel();

		steuerungsPanel.setLayout(new GridBagLayout());
		befehlsPanel.setLayout(new GridBagLayout());
		raumPanel.setLayout(new GridBagLayout());

		super.add(steuerungsPanel);
		super.add(raumPanel);
		super.add(befehlsPanel);

		GridBagConstraints c = new GridBagConstraints();
		Insets insets = new Insets(5, 5, 5, 5);
		c.insets = insets;

		c.gridx = 0;
		c.gridy = 1;
		steuerungsPanel.add(_westButton, c);

		c.gridx = 1;
		c.gridy = 0;
		steuerungsPanel.add(_northButton, c);

		c.gridy = 2;
		steuerungsPanel.add(_southButton, c);

		c.gridx = 2;
		c.gridy = 1;
		steuerungsPanel.add(_eastButton, c);

		//Befehlpanel

		c.gridx = 0;
		c.gridy = 0;
		befehlsPanel.add(_gibButton, c);

		c.gridx = 0;
		c.gridy = 1;
		befehlsPanel.add(_nehmenButton, c);

		c.gridx = 0;
		c.gridy = 2;
		befehlsPanel.add(_essenAusTascheButton, c);

		c.gridx = 0;
		c.gridy = 3;
		befehlsPanel.add(_essenBodenButton, c);

		c.gridx = 0;
		c.gridy = 4;
		befehlsPanel.add(_helpButton, c);

		c.gridx = 0;
		c.gridy = 5;
		befehlsPanel.add(_quitButton, c);

		//RaumbildPanel
		c.insets = new Insets(0, 40, 0, 40);

		c.gridx = 0;
		c.gridy = 0;

		raumPanel.add(_LabelFuerIcon, c);

		c.gridy = 6;
		befehlsPanel.add(_ladenButton, c);


	}

	//Getter und Setter für die Buttons

	/**
	 * @return den _northButton
	 */
	public JButton getNorthButton()
	{
		return _northButton;
	}

	/**
	 * @return den _southButton
	 */
	public JButton getSouthButton()
	{
		return _southButton;
	}

	/**
	 * @return den _eastButton
	 */
	public JButton getEastButton()
	{
		return _eastButton;
	}

	/**
	 * @return den _westButton
	 */
	public JButton getWestButton()
	{
		return _westButton;
	}

	/**
	 * @return den _quitButton
	 */
	public JButton getQuitButton()
	{
		return _quitButton;
	}

	/**
	 * @return den _helpButton
	 */
	public JButton getHelpButton()
	{
		return _helpButton;
	}

	/**
	 * @return den _GibButton
	 */
	public JButton getGibButton()
	{
		return _gibButton;
	}

	/**
	 * @return den _essenButton
	 */
	public JButton getEssenButton()
	{
		return _essenAusTascheButton;
	}

	/**
	 * 
	 * @return den _essenBodenButton
	 */
	public JButton getEssenBodenButton()
	{
		return _essenBodenButton;
	}

	/**
	 * @return den _nehmenButton
	 */
	public JButton getNehmenButton()
	{
		return _nehmenButton;
	}


	/**
	 * @return den _ladenButton
	 */
	public JButton getLadenButton()
	{
		return _ladenButton;
	}

	/**
	 * aktualisiert die Raumanzeige auf den nächsten Raum
	 * 
	 * @param img
	 *            Das neue Raumbild
	 */
	public void setRaumanzeige(BufferedImage img)
	{
		_raumIcon = new ImageIcon(img);
	}

}
