package de.uni_hamburg.informatik.sep.zuul.multiplayer.server.raum.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "xmlraum")
@XmlType(propOrder = { "_id", "_nordID", "_ostID", "_suedID", "_westID" })
public class XmlRaum
{
	private @XmlElement(name = "id")
	int _id;
	private @XmlElement(name = "nordid")
	int _nordID;
	private @XmlElement(name = "ostid")
	int _ostID;
	private @XmlElement(name = "suedid")
	int _suedID;
	private @XmlElement(name = "westid")
	int _westID;

	public XmlRaum()
	{
		// für JAXB
	}

	public XmlRaum(int ID, int nordID, int ostID, int suedID, int westID)
	{
		_id = ID;
		_nordID = nordID;
		_ostID = ostID;
		_suedID = suedID;
		_westID = westID;
	}

	public int getID()
	{
		return _id;
	}

	private void setID(int id)
	{
		_id = id;
	}

	public int getNordID()
	{
		return _nordID;
	}

	private void setNordID(int nordID)
	{
		_nordID = nordID;
	}

	public int getOstID()
	{
		return _ostID;
	}

	private void setOstID(int ostID)
	{
		_ostID = ostID;
	}

	public int getSuedID()
	{
		return _suedID;
	}

	private void setSuedID(int suedID)
	{
		_suedID = suedID;
	}

	public int getWestID()
	{
		return _westID;
	}

	private void setWestID(int westID)
	{
		_westID = westID;
	}
}
