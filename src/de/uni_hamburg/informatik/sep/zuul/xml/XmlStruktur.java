package de.uni_hamburg.informatik.sep.zuul.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "struktur")
public class XmlStruktur
{
	private @XmlElement(name = "xmlraum") List<XmlRaum> _raeume;
	
	public XmlStruktur()
	{
		// für JAXB
	}

	public List<XmlRaum> getRaeume()
	{
		return _raeume;
	}

	private void setRaeume(List<XmlRaum> raeume)
	{
		_raeume = raeume;
	}
}
