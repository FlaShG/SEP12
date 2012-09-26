package de.uni_hamburg.informatik.sep.zuul.server.util;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.spiel.Spieler;
/**
 * 
 * @author 0ortmann
 *
 */
public class ServerKontextTest {
	
	ServerKontext _kontext;
	Raum _startRaum;
	Raum _naechsterRaum;
	Spieler _spieler1;
	Spieler _spieler2; 
	Spieler _spieler3;
	
	@Before
	public void setUp()
	{
		_spieler1 = new Spieler("erster");
		_spieler2 = new Spieler("zweiter");
		_spieler3 = new Spieler("dritter");
		_startRaum = new Raum("start", "der startraum für den test");
		_naechsterRaum = new Raum("next", "ein anderer Raum für unseren Test");
		_kontext = new ServerKontext(_startRaum);
	}

	@Test
	public void testServerKontext() {
		
		
	}

	@Test
	public void testFuegeNeuenSpielerHinzu() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler1));
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler2));
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler3));
	
	}

	@Test
	public void testEntferneSpieler() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler1));
		_kontext.entferneSpieler(_spieler1.getName());
		assertNull(_kontext.getAktuellenRaumZu(_spieler1));
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler3));
		_kontext.wechseleRaum(_spieler3, _naechsterRaum);
		_kontext.entferneSpieler(_spieler3.getName());
		assertNull(_kontext.getAktuellenRaumZu(_spieler3));
	}

	@Test
	public void testGetAktuellenRaumZu() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler1));
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler2));
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler3));
		
		_kontext.wechseleRaum(_spieler1, _naechsterRaum);
		_kontext.wechseleRaum(_spieler2, _naechsterRaum);
		_kontext.wechseleRaum(_spieler3, _startRaum); //er bleibt hier
		
		assertEquals(_naechsterRaum,_kontext.getAktuellenRaumZu(_spieler1));
		assertEquals(_naechsterRaum,_kontext.getAktuellenRaumZu(_spieler2));
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler3));
		
	}

	@Test
	public void testSetAktuellenRaumZu() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.setAktuellenRaumZu(_spieler1, _startRaum);
		assertEquals(_startRaum,_kontext.getAktuellenRaumZu(_spieler1));
		_kontext.setAktuellenRaumZu(_spieler1, _naechsterRaum);
		assertEquals(_naechsterRaum,_kontext.getAktuellenRaumZu(_spieler1));

		
	}

	@Test
	public void testGetSpielerListe() {
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		
		assertTrue(_kontext.getSpielerListe().contains(_spieler1));
		assertTrue(_kontext.getSpielerListe().contains(_spieler2));
		assertEquals(2, _kontext.getSpielerListe().size());
		
		_kontext.entferneSpieler(_spieler2.getName());
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		assertTrue(_kontext.getSpielerListe().contains(_spieler1));
		assertTrue(_kontext.getSpielerListe().contains(_spieler3));
		assertEquals(2, _kontext.getSpielerListe().size());
		
		
	}

	@Test
	public void testGetSpielerNamenInRaum() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		assertEquals(3, _kontext.getSpielerNamenInRaum(_startRaum).size());
		assertTrue(_kontext.getSpielerNamenInRaum(_startRaum).contains(_spieler1.getName()));
		assertTrue(_kontext.getSpielerNamenInRaum(_startRaum).contains(_spieler2.getName()));
		assertTrue(_kontext.getSpielerNamenInRaum(_startRaum).contains(_spieler3.getName()));
		
		_kontext.wechseleRaum(_spieler2, _naechsterRaum);
		
		assertEquals(2, _kontext.getSpielerNamenInRaum(_startRaum).size());
		assertEquals(1, _kontext.getSpielerNamenInRaum(_naechsterRaum).size());
		assertTrue(_kontext.getSpielerNamenInRaum(_startRaum).contains(_spieler1.getName()));
		assertTrue(_kontext.getSpielerNamenInRaum(_startRaum).contains(_spieler3.getName()));
		assertTrue(_kontext.getSpielerNamenInRaum(_naechsterRaum).contains(_spieler2.getName()));
		
		
		
	}

	@Test
	public void testGetNachrichtFuer() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		
		_kontext.schreibeAnSpieler(_spieler1, "blabla text");
		_kontext.schreibeAnSpieler(_spieler2, "noch ne nachricht");
		
		assertEquals("blabla text\n", _kontext.getNachrichtFuer(_spieler1));
		assertEquals("noch ne nachricht\n", _kontext.getNachrichtFuer(_spieler2));
		
		assertNull(_kontext.getNachrichtFuer(_spieler1)); //muss nun leer sein
		assertNull(_kontext.getNachrichtFuer(_spieler2)); //muss nun leer sein
	
	}

	@Test
	public void testSchreibeAnSpieler() {
		
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		
		_kontext.schreibeAnSpieler(_spieler1, "blabla text");
		_kontext.schreibeAnSpieler(_spieler1, "anhang an alte nachricht!!");
		_kontext.schreibeAnSpieler(_spieler2, "noch ne nachricht");
		_kontext.schreibeAnSpieler(_spieler3, "den gibts nicht und es gibt auch keine nachricht");
		
		assertEquals("blabla text\nanhang an alte nachricht!!\n", _kontext.getNachrichtFuer(_spieler1));
		assertEquals("noch ne nachricht\n", _kontext.getNachrichtFuer(_spieler2));
		
		assertNull(_kontext.getNachrichtFuer(_spieler1)); //muss nun leer sein
		assertNull(_kontext.getNachrichtFuer(_spieler2)); //muss nun leer sein
	
	}

	@Test
	public void testGetSpielerByName() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		assertEquals(_spieler1, _kontext.getSpielerByName("erster"));
		assertEquals(_spieler3, _kontext.getSpielerByName("dritter"));
		
		assertNull(_kontext.getSpielerByName("kein name"));
		
	}

	@Test
	public void testGetRaeumeInDemSichSpielerAufhalten() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		
		assertEquals(2, _kontext.getRaeumeInDemSichSpielerAufhalten().size()); //alle im startraum, startraum 2 mal vorhanden.
		assertTrue(_kontext.getRaeumeInDemSichSpielerAufhalten().contains(_startRaum));
		
		_kontext.wechseleRaum(_spieler1, _naechsterRaum);
		assertEquals(2, _kontext.getRaeumeInDemSichSpielerAufhalten().size()); //jeweils einer pro raum
		assertTrue(_kontext.getRaeumeInDemSichSpielerAufhalten().contains(_startRaum));
		assertTrue(_kontext.getRaeumeInDemSichSpielerAufhalten().contains(_naechsterRaum));
		
		_kontext.wechseleRaum(_spieler2, _naechsterRaum); //alle in naechsterRaum, daher 2 mal vorhanden
		assertEquals(2, _kontext.getRaeumeInDemSichSpielerAufhalten().size()); 
		assertTrue(_kontext.getRaeumeInDemSichSpielerAufhalten().contains(_naechsterRaum));
		
		
		
	}

	@Test
	public void testGetSpielerInRaum() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		
		assertEquals(2, _kontext.getSpielerInRaum(_startRaum).size()); //zwei leute im startraum
		assertTrue(_kontext.getSpielerInRaum(_startRaum).contains(_spieler1));
		assertTrue(_kontext.getSpielerInRaum(_startRaum).contains(_spieler2));
		assertFalse(_kontext.getSpielerInRaum(_startRaum).contains(_spieler3));
		
		assertEquals(0, _kontext.getSpielerInRaum(_naechsterRaum).size());
		
		
	}

	@Test
	public void testWechseleRaum() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);

		assertEquals(_startRaum, _kontext.getAktuellenRaumZu(_spieler1));
		assertEquals(_startRaum, _kontext.getAktuellenRaumZu(_spieler2));
		assertEquals(_startRaum, _kontext.getAktuellenRaumZu(_spieler3));
		
		_kontext.wechseleRaum(_spieler1, _naechsterRaum);
		_kontext.wechseleRaum(_spieler2, _startRaum); //bleibt hier
		_kontext.wechseleRaum(_spieler3, _naechsterRaum);
		
		assertEquals(_naechsterRaum, _kontext.getAktuellenRaumZu(_spieler1));
		assertEquals(_startRaum, _kontext.getAktuellenRaumZu(_spieler2));
		assertEquals(_naechsterRaum, _kontext.getAktuellenRaumZu(_spieler3));
		
	}

	@Test
	public void testFuehreRaumGeaendertListenerAus() {
		//Nicht implementiert - schwer bis nicht testbar
	}

	@Test
	public void testGetRaumGeaendertListeners() {
		//Nicht implementiert - schwer bis nicht testbar
		//entstehunspunkt der listener außerhalb der kontextkontrolle
	}

	@Test
	public void testSpielerGewinnt() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		_kontext.spielerGewinnt(_spieler1);
		assertTrue(_spieler1.hatGewonnen());
		assertFalse(_spieler2.hatGewonnen());
		assertFalse(_spieler3.hatGewonnen());
	}

	@Test
	public void testSchreibeAnAlleSpielerInRaum() {
		_kontext.fuegeNeuenSpielerHinzu(_spieler1);
		_kontext.fuegeNeuenSpielerHinzu(_spieler2);
		_kontext.fuegeNeuenSpielerHinzu(_spieler3);
		
		_kontext.schreibeAnAlleSpielerInRaum(_startRaum, "hallo");
		
		assertEquals("hallo\n", _kontext.getNachrichtFuer(_spieler1));
		assertEquals("hallo\n", _kontext.getNachrichtFuer(_spieler2));
		assertEquals("hallo\n", _kontext.getNachrichtFuer(_spieler3));
		
		_kontext.wechseleRaum(_spieler1, _naechsterRaum);
		
		_kontext.schreibeAnAlleSpielerInRaum(_naechsterRaum, "nichts");
		assertEquals("nichts\n", _kontext.getNachrichtFuer(_spieler1));
		assertNull(_kontext.getNachrichtFuer(_spieler2));
		assertNull(_kontext.getNachrichtFuer(_spieler3));
	}

}
