package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.editor.EditorFenster;

public class EditorStartup
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				EditorFenster fenster = new EditorFenster();
			}
		});
	}

}
