package de.uni_hamburg.informatik.sep.zuul.editor;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Wrapper, der einen Ja/Nein-JDialog anzeigt und die Rückgabe als boolean
 * zurück gibt.
 * 
 * @author 0graeff
 * 
 */
public final class BestaetigungsDialog
{
	private BestaetigungsDialog()
	{

	}

	private static final String[] OPTIONS = { "Ja", "Nein" };

	/**
	 * Erstellt einen BestätigungsDialog. Siehe Klassenkommantar.
	 * 
	 * @param titel
	 *            Der Text für die Titelleiste des Dialogs.
	 * @param text
	 *            Der Text im Dialog.
	 * @return true, wenn ja gedrückt wurde, ansonsten false.
	 */
	public static boolean erstelle(String titel, String text)
	{
		return 0 == JOptionPane.showOptionDialog(new JPanel(), text, titel,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				OPTIONS, OPTIONS[0]);
	}
}
