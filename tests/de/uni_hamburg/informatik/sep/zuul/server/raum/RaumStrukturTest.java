package de.uni_hamburg.informatik.sep.zuul.server.raum;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.xml.XmlRaum;

public class RaumStrukturTest
{
	List<Raum> raumliste = new ArrayList<Raum>();
	Raum raumA = new Raum("a", "aa");
	Raum raumB = new Raum("b", "bb");
	Raum raumC = new Raum("c", "cc");
	Raum raumD = new Raum("d", "dd");

	XmlRaum xmlraumA;
	XmlRaum xmlraumB;
	XmlRaum xmlraumC;
	XmlRaum xmlraumD;
	RaumStruktur raumstruktur;

	@Before
	public void setUp() throws Exception
	{
		raumA.setAusgang("ost", raumB);
		raumB.setAusgang("west", raumA);
		raumA.setAusgang("west", raumC);
		raumC.setAusgang("ost", raumA);
		raumB.setAusgang("nord", raumD);
		raumD.setAusgang("süd", raumD);

		raumliste.add(raumA);
		raumliste.add(raumB);
		raumliste.add(raumC);
		raumliste.add(raumD);
		raumD.setRaumart(RaumArt.Ende);

		raumstruktur = new RaumStruktur(raumliste);

		xmlraumA = new XmlRaum(raumA.getId(), 0, raumA.getAusgang("ost")
				.getId(), 0, raumA.getAusgang("west").getId(), raumA.getX(),
				raumA.getY(), raumA.getItems());

		xmlraumB = new XmlRaum(raumB.getId(), raumB.getAusgang("nord").getId(),
				0, 0, raumB.getAusgang("west").getId(), raumB.getX(),
				raumB.getY(), raumB.getItems());

		xmlraumC = new XmlRaum(raumC.getId(), 0, raumC.getAusgang("ost")
				.getId(), 0, 0, raumC.getX(), raumC.getY(), raumC.getItems());

		xmlraumD = new XmlRaum(raumD.getId(), 0, 0, raumD.getAusgang("süd")
				.getId(), 0, raumD.getX(), raumD.getY(), raumD.getItems());

	}

	@Test
	public void testGetXMLRaumListe()
	{
		List<XmlRaum> xmlliste = raumstruktur.getXMLRaumListe();
		assertEquals(xmlraumA.getID(), xmlliste.get(0).getID());
		assertEquals(xmlraumA.getNordID(), xmlliste.get(0).getNordID());
		assertEquals(xmlraumA.getOstID(), xmlliste.get(0).getOstID());
		assertEquals(xmlraumA.getWestID(), xmlliste.get(0).getWestID());
		assertEquals(xmlraumA.getSuedID(), xmlliste.get(0).getSuedID());

		assertEquals(xmlraumB.getID(), xmlliste.get(1).getID());
		assertEquals(xmlraumB.getNordID(), xmlliste.get(1).getNordID());
		assertEquals(xmlraumB.getOstID(), xmlliste.get(1).getOstID());
		assertEquals(xmlraumB.getWestID(), xmlliste.get(1).getWestID());
		assertEquals(xmlraumB.getSuedID(), xmlliste.get(1).getSuedID());

		assertEquals(xmlraumC.getID(), xmlliste.get(2).getID());
		assertEquals(xmlraumC.getNordID(), xmlliste.get(2).getNordID());
		assertEquals(xmlraumC.getOstID(), xmlliste.get(2).getOstID());
		assertEquals(xmlraumC.getWestID(), xmlliste.get(2).getWestID());
		assertEquals(xmlraumC.getSuedID(), xmlliste.get(2).getSuedID());

		assertEquals(xmlraumD.getID(), xmlliste.get(3).getID());
		assertEquals(xmlraumD.getNordID(), xmlliste.get(3).getNordID());
		assertEquals(xmlraumD.getOstID(), xmlliste.get(3).getOstID());
		assertEquals(xmlraumD.getWestID(), xmlliste.get(3).getWestID());
		assertEquals(xmlraumD.getSuedID(), xmlliste.get(3).getSuedID());
	}

}
