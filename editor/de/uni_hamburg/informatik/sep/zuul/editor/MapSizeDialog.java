package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class MapSizeDialog
{
	private JDialog _dialog;
	private EigenschaftIntPanel _width;
	private EigenschaftIntPanel _height;
	
	public MapSizeDialog()
	{
		_dialog = new JDialog();
		_dialog.setTitle("Kartengröße wählen");
		_dialog.setModal(true);
		_dialog.setLayout(new FlowLayout());
		_dialog.add(_width = new EigenschaftIntPanel("Breite", 8));
		_dialog.add(_height = new EigenschaftIntPanel("Höhe", 8));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_dialog.dispose();
			}
		});
		_dialog.add(ok);
		
		//fancy größenwahn
		_dialog.pack();
		_dialog.setResizable(false);
		_dialog.setLocation(10,10);
		_dialog.setVisible(true);
	}

	public int getWidth()
	{
		return _width.getWert();
	}

	public int getHeight()
	{
		return _height.getWert();
	}
}
