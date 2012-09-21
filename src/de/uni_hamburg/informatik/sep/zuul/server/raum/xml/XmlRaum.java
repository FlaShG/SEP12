package de.uni_hamburg.informatik.sep.zuul.server.raum.xml;

import java.util.Stack;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;

@XmlRootElement(name = "xmlraum")
@XmlType(propOrder = { "_id", "_nordID", "_ostID", "_suedID", "_westID", "_x", "_y", "_items"})
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
	private @XmlElement(name = "x")
	int _x;
	private @XmlElement(name = "y")
	int _y;
	private @XmlElement(name = "item")
	Stack<Item> _items;
	

	public XmlRaum()
	{
		// für JAXB
		_items = new Stack<Item>();
	}

	/**
	 * 
	 * @param ID
	 * @param nordID
	 * @param ostID
	 * @param suedID
	 * @param westID
	 * @param x
	 * @param y
	 * @param items
	 * 
	 * @require items != null
	 */
	public XmlRaum(int ID, int nordID, int ostID, int suedID, int westID,
			int x, int y, Stack<Item> items)
	{
		assert items != null : "Vorbedingung verletzt: items != null";
		
		_id = ID;
		_nordID = nordID;
		_ostID = ostID;
		_suedID = suedID;
		_westID = westID;
		_x = x;
		_y = y;
		_items = items;
	}

	public int getX()
	{
		return _x;
	}

	public int getY()
	{
		return _y;
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

	
	public Stack<Item> getItems()
	{
		return _items;
	}
	

	private void setItems(Stack<Item> items)
	{
		_items = items;
	}
}
