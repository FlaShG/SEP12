package de.uni_hamburg.informatik.sep.zuul.client.oberflaeche.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	
	private JLabel _labelFuerLebensenergie;

	private static final long serialVersionUID = 1L;

	public BefehlsPanel()
	{
		setLayout(null);
		
		_labelFuerLebensenergie = new JLabel();
		_labelFuerLebensenergie.setLocation(0,0);

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

		_helpButton = new JButton(new ImageIcon(PATH + "faq-icon.png"));
		_helpButton.setContentAreaFilled(false);
		_helpButton.setBorderPainted(false);
		_helpButton.setFocusable(false);
		

		_ladenButton = new JButton();
		_ladenButton.setContentAreaFilled(false);
		_ladenButton.setBorderPainted(false);
		_ladenButton.setFocusable(false);
		

		_quitButton = new JButton(new ImageIcon(PATH+ "exitIcon.gif") );
		_quitButton.setContentAreaFilled(false);
		_quitButton.setBorderPainted(false);
		_quitButton.setFocusable(false);

		_helpButton.setSize(50, 50);
		_ladenButton.setSize(50, 50);
		_quitButton.setSize(50, 50);

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

				int buttonhoehespalte1 = (BefehlsPanel.this.getHeight() - (platzhalter * 2 * (4))) / 4;

				int buttonhoehespalte2 = (BefehlsPanel.this.getHeight() - 50 - (platzhalter * 2 * (3))) / 3;

				Dimension buttongroesse1 = new Dimension(
						BefehlsPanel.this.getWidth() / 2 - 25,
						buttonhoehespalte1);

				Dimension buttongroesse2 = new Dimension(
						BefehlsPanel.this.getWidth() / 2 - 25,
						buttonhoehespalte2);

				//linke spalte :

				_nehmenButton.setSize(buttongroesse1);
				_gibButton.setSize(buttongroesse1);
				_fuettereButton.setSize(buttongroesse1);
				_essenBodenButton.setSize(buttongroesse1);

				//rechte Spalte
				_inventarButton.setSize(buttongroesse2);
				_ablegenButton.setSize(buttongroesse2);
				_essenAusTascheButton.setSize(buttongroesse2);

				//linke Spalte

				_nehmenButton.setLocation(5, 5);
				_gibButton.setLocation(5,
						(int) (buttongroesse1.getHeight() + 10));
				_fuettereButton.setLocation(5,
						(int) (buttongroesse1.getHeight() * 2 + 15));
				_essenBodenButton.setLocation(5,
						(int) (buttongroesse1.getHeight() * 3 + 20));

				//rechte Spalte

				int x = _nehmenButton.getWidth() + 10;

				_inventarButton.setLocation(x, 5);
				_ablegenButton.setLocation(x,
						(int) (buttongroesse2.getHeight() + 10));
				_essenAusTascheButton.setLocation(x,
						(int) (buttongroesse2.getHeight() * 2 + 15));

				_helpButton.setLocation(_inventarButton.getX()
						+ _inventarButton.getWidth() / 2,
						BefehlsPanel.this.getHeight() - 50);
				_ladenButton.setLocation(
						_helpButton.getX() + _helpButton.getWidth() + 5,
						BefehlsPanel.this.getHeight() - 50);
				_quitButton.setLocation(
						_ladenButton.getX() + _ladenButton.getWidth() + 5,
						BefehlsPanel.this.getHeight() - 50);
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
	
	public void setLebensenergie(int lebensenergie)
	{
//		if(this.getWidth() != 0 && this.getHeight() != 0)
//		{
//			BufferedImage leben = new BufferedImage(30, this.getWidth(),
//					BufferedImage.TYPE_INT_ARGB);
//			Graphics g = leben.getGraphics();
//			g.setColor(Color.red);
//			g.fill3DRect(0, 0, 30, this.getWidth(), true);
//			_LabelFuerIcon.setIcon(new ImageIcon(leben));
//		}
	}

}
