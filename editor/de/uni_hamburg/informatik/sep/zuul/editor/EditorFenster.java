package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

/**
 * Die Werkzeugklasse für das Editorfenser.
 * @author 0graeff
 *
 */
public class EditorFenster implements Observer
{
	private EditorFensterUI _ui;
	
	public EditorFenster()
	{
		_ui = new EditorFensterUI(this);
	}
	
	private void registriereUIAktionen()
	{
		_ui.getRaumhinzu().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_ui.getMap().fuegeRaumZuAktivemButtonHinzu();
				if(_ui.getMap().getAktivenRaum() != null)
				{
					 update(null, null);
				}
			}
		});
	}
	
	@Override
	public void update(Observable arg0, Object arg1)
	{
		//DONT USE ARGS!!!
		
		_ui.getFrame().remove(_ui.getRaumhinzu());
		if(_ui.getBearbeitenPanel() != null)
			_ui.getFrame().remove(_ui.getBearbeitenPanel());
		_ui.getFrame().setVisible(true);
		
		if(_ui.getMap().buttonAusgewaehlt())
		{
			Raum raum = _ui.getMap().getAktivenRaum();
			if(raum == null)
			{
				_ui.getFrame().add(_ui.getRaumhinzu(), BorderLayout.SOUTH);
			}
			else
			{
				_ui.getFrame().add(_ui.neuesBearbeitenPanel(raum), BorderLayout.SOUTH);
			}
		}
		_ui.getFrame().setVisible(true);
	}
}
