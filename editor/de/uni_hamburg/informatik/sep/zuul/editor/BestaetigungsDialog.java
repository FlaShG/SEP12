package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class BestaetigungsDialog
{
	private BestaetigungsDialog()
	{

	}

	private static final String[] OPTIONS = { "Ja", "Nein" };

	public static boolean erstelle(String titel, String text)
	{
		return 0 == JOptionPane.showOptionDialog(new JPanel(), text, titel,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				OPTIONS, OPTIONS[0]);
	}
}
