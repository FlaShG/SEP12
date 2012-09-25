package de.uni_hamburg.informatik.sep.zuul.server.raum.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "struktur")
public class XmlStruktur
{
	private @XmlElement(name = "leben")
	int _leben;
	private @XmlElement(name = "maeuse")
	int _maeuse;
	private @XmlElement(name = "katzen")
	int _katzen;
	private @XmlElement(name = "xmlraum")
	List<XmlRaum> _raeume;

	public XmlStruktur()
	{
		// für JAXB
	}

	@XmlTransient
	public List<XmlRaum> getRaeume()
	{
		return _raeume;
	}

	public void setRaeume(List<XmlRaum> raeume)
	{
		_raeume = raeume;
	}

	@XmlTransient
	public int getMaeuse()
	{
		return _maeuse;
	}

	public void setMaeuse(int maeuse)
	{
		this._maeuse = maeuse;
	}

	@XmlTransient
	public int getKatzen()
	{
		return _katzen;
	}

	public void setKatzen(int katzen)
	{
		_katzen = katzen;
	}

	@XmlTransient
	public int getLeben()
	{
		return _leben;
	}

	public void setLeben(int leben)
	{
		_leben = leben;
	}
}
