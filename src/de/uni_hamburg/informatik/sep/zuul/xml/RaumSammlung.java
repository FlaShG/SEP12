package de.uni_hamburg.informatik.sep.zuul.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raum;

@XmlRootElement(name = "raumsammlung")
public class RaumSammlung
{
	private @XmlElement(name = "raum")
	List<Raum> _sammlung;

	public RaumSammlung()
	{
		_sammlung = new ArrayList<Raum>();
	}

	public List<Raum> getSammlung()
	{
		return _sammlung;
	}

	private void setSammlung(List<Raum> sammlung)
	{
		_sammlung = sammlung;
	}
}
