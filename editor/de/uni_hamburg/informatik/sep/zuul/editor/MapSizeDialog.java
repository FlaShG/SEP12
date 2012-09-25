package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * Kleiner Dialog, in dem eine Höhe und eine Breite eingestellt werden können. Modaler {@link JDialog}.
 * @author 0graeff
 *
 */
public class MapSizeDialog
{
	private JDialog _dialog;
	private EigenschaftIntPanel _width;
	private EigenschaftIntPanel _height;
	private boolean _ok;

	/**
	 * Erstellt einen neuen {@link MapSizeDialog} mit der Größe 8x8 als Startwert.
	 */
	public MapSizeDialog()
	{
		this(8,8);
	}

	/**
	 * Erstellt einen neuen MapSizedialog mit gegebenen Startwerten.
	 * @param breite vorgeschlagene Breite
	 * @param hoehe vorgeschlagene Höhe
	 */
	public MapSizeDialog(int breite, int hoehe)
	{
		_dialog = new JDialog();
		_dialog.setTitle("Kartengröße wählen");
		_dialog.setModal(true);
		_dialog.setLayout(new FlowLayout());
		_dialog.add(_width = new EigenschaftIntPanel("Breite", breite));
		_dialog.add(_height = new EigenschaftIntPanel("Höhe", hoehe));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_ok = true;
				_dialog.dispose();
			}
		});
		_dialog.add(ok);
		
		//fancy größenwahn
		_dialog.pack();
		_dialog.setResizable(false);
		//_dialog.setLocation(10,10);
		_dialog.setLocationRelativeTo(SwingUtilities.getRoot(_dialog));
		_dialog.setVisible(true);
	}

	/**
	 * Gibt die eingestellte Breite zurück.
	 */
	public int getBreite()
	{
		return _width.getWert();
	}

	/**
	 * Gibt die eingestellte Höhe zurück.
	 */
	public int getHoehe()
	{
		return _height.getWert();
	}
	
	/**
	 * Gibt zurück, ob ok geklickt wurde.
	 * Die Alternative wäre ein Abbruch durch Schließen des Fensters.
	 */
	public boolean getClickedOK()
	{
		return _ok;
	}
}
