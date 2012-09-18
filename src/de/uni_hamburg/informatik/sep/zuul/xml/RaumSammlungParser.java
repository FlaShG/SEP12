package de.uni_hamburg.informatik.sep.zuul.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

public class RaumSammlungParser
{
	private final String XMLPATH = "./xml_dateien/RaumSammlung.xml";
	private RaumSammlung _root;
	
	public RaumSammlungParser()
	{
		leseXmlEin();
		
	}
	
	public List<Raum> getSammlung()
	{
		return _root.getSammlung();
	}
	
	private void leseXmlEin()
	{
		try
		{
			File file = new File(XMLPATH);
			JAXBContext jcontext = JAXBContext.newInstance(RaumSammlung.class);
			Unmarshaller junmarshaller = jcontext.createUnmarshaller();
			_root = (RaumSammlung) junmarshaller.unmarshal(file);
		}
		catch (Exception e)
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
			JAXBContext jcontext = JAXBContext.newInstance(RaumSammlung.class);
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
