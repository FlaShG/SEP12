package de.uni_hamburg.informatik.sep.zuul.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

	public List<XmlRaum> getXmlVerbindungen()
	{
		return _root.getRaeume();
	}

	/**
	 * Liest die StrukturXml ein.
	 */
	private void leseXmlEin()
	{
		try
		{
			File file = new File(XMLPATH);
			if(!file.exists())
				erstelleXml();
			
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

	/**
	 * Schreibt die Liste an XmlRäumen (getXmlVerbindungen)
	 * in die im Konstruktor angegebene Datei.
	 */
	public void schreibeXml()
	{
		try
		{
			File file = new File(XMLPATH);
			if (!file.exists())
				erstelleXml();
			
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

	/**
	 * Erstellt grundlegende Xml-Datei
	 */
	private void erstelleXml() throws Exception
	{
		File file = new File(XMLPATH);
		if (file.createNewFile())
		{
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(new FileWriter(file));
				writer.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
				writer.write("<struktur>\n");
				writer.write("</struktur>");
				writer.flush();
			}
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				if (writer != null)
				{
					writer.close();
				}
			}
		}
		
		
	}
	
	/**
	 * Validiert die angegebene xml.
	 */
	public static boolean validiere(String path)
	{
		try
		{	// wäre schön, wenn xsd dateien zur validierung genutzt werde
			// könnten, ist aber jetzt nicht schlimm :P
			File file = new File(path);
			JAXBContext jcontext = JAXBContext.newInstance(XmlStruktur.class);
			Unmarshaller junmarshaller = jcontext.createUnmarshaller();
			junmarshaller.unmarshal(file);
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
