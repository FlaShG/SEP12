package de.uni_hamburg.informatik.sep.zuul.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class RaumStrukturParser
{

	private final String XMLPATH;
	private XmlStruktur _root;

	/**
	 * Erstellt einen neuen {@link RaumStrukturParser} und liest die angegebene
	 * <code>.xml</code>
	 * 
	 * @param xmlpath
	 */
	public RaumStrukturParser(String xmlpath)
	{
		assert xmlpath != null : "Vorbedingung verletzt: xmlpath != null";

		XMLPATH = xmlpath;
		leseXmlEin();
	}

	/**
	 * Gibt die XmlRäume ({@link XmlRaum}) zurück.
	 * Diese Liste ist zu bearbeiten, wenn die Informationen
	 * auch gespeichert werde sollen.
	 */
	public List<XmlRaum> getXmlVerbindungen()
	{
		return _root.getRaeume();
	}

	/**
	 * Gibt die Azahl der Mäuse für dieses Level zurück.
	 */
	public int getAnzahlMaeuse()
	{
		return _root.getMaeuse();
	}
	
	/**
	 * Setzt die Anzahl Mäuse für dieses Level.
	 */
	public void setAnzahlMaeuse(int maeuse)
	{
		_root.setMaeuse(maeuse);
	}
	
	private void leseXmlEin()
	{
		try
		{
			File file = new File(XMLPATH);
			JAXBContext jcontext = JAXBContext.newInstance(XmlStruktur.class);
			Unmarshaller junmarshaller = jcontext.createUnmarshaller();
			_root = (XmlStruktur) junmarshaller.unmarshal(file);
		}
		catch(Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void schreibeXml()
	{
		try
		{
			File file = new File(XMLPATH);
			JAXBContext jcontext = JAXBContext.newInstance(XmlStruktur.class);
			Marshaller jmarshaller = jcontext.createMarshaller();
			jmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jmarshaller.marshal(_root, file);
		}
		catch(Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
