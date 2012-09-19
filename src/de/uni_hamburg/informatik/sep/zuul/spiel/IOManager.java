package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.sep.zuul.xml.RaumSammlungParser;
import de.uni_hamburg.informatik.sep.zuul.xml.RaumStrukturParser;
import de.uni_hamburg.informatik.sep.zuul.xml.XmlRaum;

public class IOManager
{
	private RaumStrukturParser strukParser;
	private RaumSammlungParser sammlParser;

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
	 */
	public void schreibeLevelStruktur(String path, RaumStruktur raumStruktur)
	{
		strukParser = new RaumStrukturParser(path);
		strukParser.getXmlVerbindungen().clear();
		for(XmlRaum raum : raumStruktur.getXMLRaumListe())
		{
			strukParser.getXmlVerbindungen().add(raum);
		}

		strukParser.schreibeXml();
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
		sammlParser = new RaumSammlungParser();

		//ID Liste der vorhandenen Räume
		List<Integer> idSammlung = new ArrayList<Integer>();
		for(Raum raum : sammlParser.getSammlung())
		{
			idSammlung.add(raum.getId());
		}

		for(Raum raum : raumListe)
		{
			if(!idSammlung.contains(raum.getId()))
			{
				sammlParser.getSammlung().add(raum);
			}
		}

		sammlParser.schreibeXml();

	}

	/**
	 * Lies mit den Parsern ein Level von einem Dateipfad ein.
	 * 
	 * @param path
	 */
	public void readLevel(String path)
	{
		strukParser = new RaumStrukturParser(path);

		sammlParser = new RaumSammlungParser();
	}

	/**
	 * Getter für die XML-Raum Liste die eingelesen wurde.
	 * 
	 * @return XmlRaum Liste
	 */
	public List<XmlRaum> getXmlRaeume()
	{
		return strukParser.getXmlVerbindungen();
	}

	/**
	 * Getter für die Raum Liste die eingelesen wurde.
	 * 
	 * @return Raum Liste
	 */
	public List<Raum> getRaeume()
	{
		return sammlParser.getSammlung();
	}
}
