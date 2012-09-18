package de.uni_hamburg.informatik.sep.zuul.editor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class EditorFensterUI
{
	private JFrame _frame;
	
	
	
	public EditorFensterUI()
	{
		_frame = new JFrame("Zuul-Editor");
		
		_frame.getContentPane().setLayout(new BorderLayout());
		
		
		_frame.add(new EditorMenuBar(), BorderLayout.NORTH);
		_frame.add(new EditorMap(8, 8), BorderLayout.CENTER);
		_frame.add(new RaumBearbeitenPanel(), BorderLayout.SOUTH);
		
		_frame.pack();
		_frame.setMinimumSize(_frame.getSize());
		_frame.setSize(_frame.getSize().width, _frame.getSize().width);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);
	}
}
