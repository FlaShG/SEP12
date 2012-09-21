package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.editor.EditorLevel;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.RaumSammlungParser;
import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.RaumStrukturParser;
import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.XmlRaum;

public class IOManager
{
	private RaumStrukturParser _strukParser;
	private RaumSammlungParser _sammlParser;

	public IOManager()
	{
		//Tut noch nichts, relevant für Nutzer sind vorerst nur read und write.
	}

	/**
	 * Schreibe eine SpiellevelStruktur als XML Datei nach draußen.
	 * 
	 * @param path
	 *            Pfad auf den geschrieben werden soll
	 * @param raumStruktur
	 *            die aktuelle RaumStruktur
	 * @param level
	 * 			  das EditorLevel, welches die Levelinformationen enthält
	 */
	public void schreibeLevelStruktur(String path, RaumStruktur raumStruktur, EditorLevel level)
	{
		_strukParser = new RaumStrukturParser(path);
		_strukParser.setAnzahlMaeuse(level.getMaeuse());
		_strukParser.setXmlVerbindungen(raumStruktur.getXMLRaumListe());
//		_strukParser.getXmlVerbindungen().clear();
//		for(XmlRaum raum : raumStruktur.getXMLRaumListe())
//		{
//			_strukParser.getXmlVerbindungen().add(raum);
//		}
		

		_strukParser.schreibeXml();
	}

	/**
	 * Schreibe eine Liste von Räumen als XML nach draußen.
	 * 
	 * @param path
	 *            Pfad auf den geschrieben werden soll
	 * @param raumListe
	 *            die aktuelle RaumListe
	 */
	public void schreibeLevelRaeume(List<Raum> raumListe)
	{
		readRaeume();

		//ID Liste der vorhandenen Räume
		List<Integer> idSammlung = new ArrayList<Integer>();
		for(Raum raum : _sammlParser.getSammlung())
		{
			idSammlung.add(raum.getId());
		}

		for(Raum raum : raumListe)
		{
			if(!idSammlung.contains(raum.getId()))
			{
				_sammlParser.getSammlung().add(raum);
			}
		}

		_sammlParser.schreibeXml();

	}

	/**
	 * Lies mit den Parsern ein Level von einem Dateipfad ein.
	 * 
	 * @param path
	 */
	public void readLevel(String path)
	{
		_strukParser = new RaumStrukturParser(path);

		readRaeume();
	}

	public void readRaeume()
	{
		_sammlParser = new RaumSammlungParser();
	}

	/**
	 * Gibt true zurück, wenn die angegebene .xml
	 * einen valide Struktur besitzt.
	 * Es geht hierbei um die LevelXml!
	 * 
	 * @param path
	 * 			Pfad der xml-Datei
	 */
	public static boolean validiereLevel(String path)
	{
		return RaumStrukturParser.validiere(path);
	}
	
	/**
	 * Getter für die XML-Raum Liste die eingelesen wurde.
	 * 
	 * @return XmlRaum Liste
	 */
	public List<XmlRaum> getXmlRaeume()
	{
		return _strukParser.getXmlVerbindungen();
	}

	/**
	 * Getter für die Raum Liste die eingelesen wurde.
	 * 
	 * @return Raum Liste
	 */
	public List<Raum> getRaeume()
	{
		return _sammlParser.getSammlung();
	}
	
	/**
	 * Gibt das {@link EditorLevel} zurück, welches die
	 * Levelinformationen hält
	 */
	public EditorLevel getEditorLevel()
	{
		EditorLevel level = new EditorLevel();
		level.setMaeuse(_strukParser.getAnzahlMaeuse());
		return level;
	}
}
