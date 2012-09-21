package de.uni_hamburg.informatik.sep.zuul.client;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

/**
 * Die Klasse ist dafür verantwortlich, einen Dateiauswahl Dialog zu zeigen und
 * die ausgewählte Datei als String zu übergeben.
 * 
 * @author 1fechner
 * 
 */
public class FileChooser
{
	/**
	 * Privater Konstruktor, es soll kein FileChooser erzeugt werden. Die
	 * Methode ist statisch.
	 */
	private FileChooser()
	{

	}

	/**
	 * Öffnet eine Datei, und gibt den Pfad als String zurück. Wenn die
	 * geöffnete Datei keine gültige Datei ist oder der Vorgang abgebrochen
	 * wurde, wird <code>null</code> zurückgegeben. Es können nur gültige
	 * Zuul-Level Dateien angezeigt werden.
	 * 
	 * @return Den Dateipfad als String oder <code>null</code>, wenn die Datei
	 *         ungültig ist.
	 */
	public static String oeffneDatei()
	{
		String level = null;
		final JFileChooser fileChooser = konfiguriereFileChooser();

		int returnVal = fileChooser.showOpenDialog(new JPanel());

		if(returnVal == JFileChooser.APPROVE_OPTION)
		{

			File file = fileChooser.getSelectedFile();
			level = file.getAbsolutePath();
		}
		return level;
	}

	/**
	 * Gibt den Speicherpfad der Datei zurück. Es können nur gültige Zuul-Level
	 * Dateien angezeigt werden.
	 * 
	 * @return Den Dateipfad als String oder <code>null</code>, wenn die Datei
	 *         ungültig ist.
	 */
	public static String speichereDatei()
	{
		String level = null;
		final JFileChooser fileChooser = konfiguriereFileChooser();

		int returnVal = fileChooser.showSaveDialog(new JPanel());

		if(returnVal == JFileChooser.APPROVE_OPTION)
		{

			File file = fileChooser.getSelectedFile();
			level = file.getAbsolutePath();
		}
		return level;
	}

	private static JFileChooser konfiguriereFileChooser()
	{
		final JFileChooser fileChooser = new JFileChooser("./xml_dateien/");
		fileChooser.setFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				return "Zuul-Level";
			}

			@Override
			public boolean accept(File arg0)
			{
				return (isFileXML(arg0) && isFileValidLevel(arg0))
						|| isDirectory(arg0);
			}

		});
		fileChooser.setAcceptAllFileFilterUsed(false);
		return fileChooser;
	}

	/**
	 * Gibt an, ob die Datei eine XML ist.
	 * 
	 * @param file
	 * @return boolean, ist Datei XML oder nicht
	 */
	private static boolean isFileXML(File file)
	{
		return file.getName().toLowerCase().endsWith(".xml");
	}

	/**
	 * Gibt an, ob die Datei dem XML-konventionen für Zuul-Level entspricht.
	 * 
	 * @param file
	 * @return boolean, ist die Datei ein gültiges Level oder nicht
	 */
	private static boolean isFileValidLevel(File file)
	{
		return IOManager.validiereLevel(file.getAbsolutePath());
	}

	/**
	 * Gibt an, ob die Datei ein Verzeichnis ist oder nicht.
	 * 
	 * @param file
	 * @return booean, ob die Datei ein Verzeichnis ist.
	 */
	private static boolean isDirectory(File file)
	{
		return file.isDirectory();
	}
}
