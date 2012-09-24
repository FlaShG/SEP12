package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.util.StringUtils;

import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class BefehlsPanel extends JPanel
{
	//TODO ANZAHLBUTTONS BEI NEUEN BEFEHLEN ANPASSEN!!!
	private final String PATH = getClass().getResource("bilder/").getPath();
	private final int ZEILENANZAHL = 5;
	private final String GUT = "Gut";
	private final String SCHLECHT = "Giftig";
	private final String UNBEKANNT = "Unbekannt";
	private JButton _quitButton;
	private JButton _helpButton;
	private JButton _essenTascheButton;
	private JButton _essenTascheGutButton;
	private JButton _essenTascheSchlechtButton;
	private JButton _essenTascheUnbekanntButton;
	private JButton _nehmenButton;
	private JButton _gibButton;
	private JButton _essenBodenButton;
	private JButton _ladenButton;
	private JButton _fuettereButton;
	private JButton _fuettereGutButton;
	private JButton _fuettereSchlechtButton;
	private JButton _fuettereUnbekanntButton;
	private JButton _ablegenButton;
	private JButton _ablegenGutButton;
	private JButton _ablegenSchlechtButton;
	private JButton _ablegenUnbekanntButton;
	private JButton _inventarButton;
	private JButton[] _normalButtons;
	private JButton[] _extraButtons;

	private JLabel _labelFuerLebensenergie;

	private static final long serialVersionUID = 1L;

	public BefehlsPanel()
	{
		setLayout(null);

		_labelFuerLebensenergie = new JLabel();
		_labelFuerLebensenergie.setLocation(0, 0);

		_nehmenButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_NEHMEN));

		_nehmenButton.setFocusable(false);

		_gibButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_GIB));

		_gibButton.setFocusable(false);

		_fuettereButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_FEED));

		_fuettereButton.setFocusable(false);

		_fuettereGutButton = new JButton(GUT);
		_fuettereGutButton.setFocusable(false);

		_fuettereSchlechtButton = new JButton(SCHLECHT);
		_fuettereSchlechtButton.setFocusable(false);

		_fuettereUnbekanntButton = new JButton(UNBEKANNT);
		_fuettereUnbekanntButton.setFocusable(false);

		_essenTascheButton = new JButton(
				StringUtils.capitalize(TextVerwalter.BEFEHL_ESSEN + " "
						+ TextVerwalter.ORT_TASCHE));
		_essenTascheButton.setFocusable(false);

		_essenTascheGutButton = new JButton(GUT);
		_essenTascheGutButton.setFocusable(false);

		_essenTascheSchlechtButton = new JButton(SCHLECHT);
		_essenTascheSchlechtButton.setFocusable(false);

		_essenTascheUnbekanntButton = new JButton(UNBEKANNT);
		_essenTascheUnbekanntButton.setFocusable(false);

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

		_ablegenGutButton = new JButton(GUT);
		_ablegenGutButton.setFocusable(false);

		_ablegenSchlechtButton = new JButton(SCHLECHT);
		_ablegenSchlechtButton.setFocusable(false);

		_ablegenUnbekanntButton = new JButton(UNBEKANNT);
		_ablegenUnbekanntButton.setFocusable(false);

		_helpButton = new JButton(new ImageIcon(PATH + "faq-icon.png"));
		_helpButton.setFocusable(false);

		_ladenButton = new JButton(new ImageIcon(PATH + "disk.gif"));
		_ladenButton.setFocusable(false);

		_quitButton = new JButton(new ImageIcon(PATH + "exitIcon.gif"));
		_quitButton.setBackground(new Color(0,175,0));
		_quitButton.setFocusable(false);

		_normalButtons = new JButton[]{_nehmenButton, _gibButton,
				_fuettereButton, _essenBodenButton, _essenTascheButton,
				_inventarButton, _ablegenButton};
		_extraButtons = new JButton[]{_fuettereGutButton,
				_fuettereSchlechtButton, _fuettereUnbekanntButton,
				_ablegenGutButton, _ablegenSchlechtButton,
				_ablegenUnbekanntButton, _essenTascheGutButton,
				_essenTascheSchlechtButton, _essenTascheUnbekanntButton};
		
		_helpButton.setSize(50, 50);
		_ladenButton.setSize(50, 50);
		_quitButton.setSize(50, 50);

		add(_nehmenButton);
		add(_gibButton);
		add(_fuettereButton);
		add(_fuettereGutButton);
		add(_fuettereSchlechtButton);
		add(_fuettereUnbekanntButton);
		add(_essenTascheButton);
		add(_essenTascheGutButton);
		add(_essenTascheSchlechtButton);
		add(_essenTascheUnbekanntButton);
		add(_essenBodenButton);
		add(_inventarButton);
		add(_ablegenButton);
		add(_ablegenGutButton);
		add(_ablegenSchlechtButton);
		add(_ablegenUnbekanntButton);
		add(_helpButton);
		add(_ladenButton);
		add(_quitButton);
		add(_labelFuerLebensenergie);

		addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				int platzhalter = 5;

				int buttonhoehe = (BefehlsPanel.this.getHeight() - (platzhalter * 2 * (ZEILENANZAHL)))
						/ ZEILENANZAHL;

				int buttonhoeheExtra = (int) (buttonhoehe * 0.75);

				Dimension buttongroesse = new Dimension(
						(BefehlsPanel.this.getWidth() - 100) / 2 -25,
						buttonhoehe);

				Dimension buttongroesseExtra = new Dimension(
						buttongroesse.width / 3, buttonhoeheExtra);

				// TODO
				for(JButton b : _normalButtons)
				{
					b.setSize(buttongroesse);
				}

				for(JButton b : _extraButtons)
				{
					b.setSize(buttongroesseExtra);
				}

				//linke Spalte
				int xpos_1 = 65;

				_nehmenButton.setLocation(xpos_1, 5);
				_gibButton.setLocation(xpos_1, (int) (buttongroesse.getHeight() + 10));
				_essenBodenButton.setLocation(xpos_1,(int) (buttongroesse.getHeight() * 2 + 15));
				_fuettereButton.setLocation(xpos_1,
						(int) (buttongroesse.getHeight() * 3 + 20));

				_fuettereGutButton.setLocation(xpos_1,
						(int) (_fuettereButton.getY() + buttonhoehe + 2));
				_fuettereSchlechtButton.setLocation(
						xpos_1 + _fuettereGutButton.getWidth(),
						(int) (_fuettereButton.getY() + buttonhoehe + 2));
				_fuettereUnbekanntButton.setLocation(
						_fuettereSchlechtButton.getLocation().x
								+ _fuettereSchlechtButton.getWidth(),
						(int) (_fuettereButton.getY() + buttonhoehe + 2));


				//rechte Spalte

				int x = _nehmenButton.getWidth()
						+ _nehmenButton.getLocation().x + 10;

				_inventarButton.setLocation(x, 5);
				
				_ablegenButton.setLocation(x, buttonhoehe + 10);

				_ablegenGutButton.setLocation(x,
						(int) (_ablegenButton.getY() + buttonhoehe + 2));
				_ablegenSchlechtButton.setLocation(
						_ablegenGutButton.getLocation().x
								+ buttongroesseExtra.width,
						_ablegenButton.getY() + buttonhoehe + 2);

				_ablegenUnbekanntButton.setLocation(
						_ablegenSchlechtButton.getLocation().x
								+ buttongroesseExtra.width,
								_ablegenButton.getY() + buttonhoehe + 2);

				_essenTascheButton.setLocation(
						x,
						(int) (buttongroesse.getHeight() * 3 + 20));

				_essenTascheGutButton.setLocation(x,
						(int) (_essenTascheButton.getY() + buttonhoehe + 2));
				_essenTascheSchlechtButton.setLocation(x
						+ buttongroesseExtra.width,
						(int) (_essenTascheButton.getY() + buttonhoehe + 2));
				_essenTascheUnbekanntButton.setLocation(
						_essenTascheSchlechtButton.getX()
								+ buttongroesseExtra.width,
						(int) (_essenTascheButton.getY() + buttonhoehe + 2));

				// system buttons
				_helpButton.setLocation(_inventarButton.getX()
						+ _inventarButton.getWidth() + 5, 5);

				_ladenButton.setLocation(_inventarButton.getX()
						+ _inventarButton.getWidth() + 5,
						_helpButton.getY() + _helpButton.getHeight() + 5);
				
				_quitButton.setLocation(_inventarButton.getX()
						+ _inventarButton.getWidth() + 5,
						_ladenButton.getY() + _ladenButton.getHeight() + 5);

				_labelFuerLebensenergie.setSize(50,
						BefehlsPanel.this.getHeight());
				setLebensenergie(16);

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
		return _essenTascheButton;
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

	public void setLebensenergie(int lebensenergie)
	{
		if(_labelFuerLebensenergie.getHeight() != 0
				&& _labelFuerLebensenergie.getWidth() != 0)
		{
			BufferedImage lebensbalken = new BufferedImage(50,
					_labelFuerLebensenergie.getHeight(),
					BufferedImage.TYPE_INT_ARGB);

			int maxLife = 18;
			int currentlife = lebensenergie;

			int balkenhoehe = (int) (_labelFuerLebensenergie.getHeight() / 100.0 * (currentlife / (maxLife / 100.0)));

			GradientPaint gp = new GradientPaint(new Point2D.Double(0,
					_labelFuerLebensenergie.getHeight() - balkenhoehe),
					Color.red, new Point2D.Double(50,
							_labelFuerLebensenergie.getHeight()), Color.blue);

			Graphics2D g2d = (Graphics2D) lebensbalken.getGraphics();
			g2d.setPaint(gp);
			Rectangle rec = new Rectangle(0,
					_labelFuerLebensenergie.getHeight() - balkenhoehe, 50,
					balkenhoehe);
			g2d.fill(rec);
			g2d.draw(rec);

			_labelFuerLebensenergie.setIcon(new ImageIcon(lebensbalken));
		}

	}

}
