package de.uni_hamburg.informatik.sep.zuul;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.sep.zuul.editor.EditorFenster;

/**
 * main-Klasse für Editor.
 * @author 0graeff
 *
 */
public final class EditorStartup
{
	private EditorStartup()
	{
		
	}

	/**
	 * @param string ignoriert.
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new EditorFenster();
			}
		});
	}

}
