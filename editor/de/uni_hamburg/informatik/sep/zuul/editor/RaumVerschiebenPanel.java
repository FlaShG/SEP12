package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RaumVerschiebenPanel extends JPanel
{
	private JButton _nord;
	private JButton _west;
	private JButton _ost;
	private JButton _sued;
	private JButton _warp;
	private EditorBeobachter _beobachter;
	
	public RaumVerschiebenPanel(EditorBeobachter beobachter)
	{
		_beobachter = beobachter;
		
		JPanel inner = new JPanel();
		
		setLayout(new FlowLayout());
		add(inner);
		
		inner.setLayout(new GridLayout(3,3));
		Dimension dim = new Dimension(200,200);
		inner.setSize(dim);
		inner.setPreferredSize(dim);
		inner.setMinimumSize(dim);
		inner.setMaximumSize(dim);
		
		inner.add(new JLabel(""));
		inner.add(_nord = new JButton("^"));
		inner.add(new JLabel(""));
		
		inner.add(_west = new JButton("<"));
		inner.add(_warp = new JButton("warp"));
		inner.add(_ost = new JButton(">"));
		
		inner.add(new JLabel(""));
		inner.add(_sued = new JButton("v"));
		inner.add(new JLabel(""));
		
		_nord.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				informiereBeobachter(0,-1);
			}
		});
		_west.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				informiereBeobachter(-1,0);
			}
		});
		_ost.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				informiereBeobachter(1,0);
			}
		});
		_sued.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				informiereBeobachter(0,1);
			}
		});
		
		_warp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_beobachter.warpUpdate();
			}
		});
	}
	
	private void informiereBeobachter(int x, int y)
	{
		_beobachter.verschiebenUpdate(x, y);
	}
}
