package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusfuehrenListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.Feature;
import de.uni_hamburg.informatik.sep.zuul.server.features.RaumGeaendertListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumStruktur;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class SpielLogikTest {
	
	SpielLogik _testLogik = new SpielLogik();
	Spieler _spieler1;
	Spieler _spieler2;
	Raum _start;
	Raum _ziel;
	
	
	@Before
	public void setUp()
	{
		_spieler1 = mock(Spieler.class);
		_spieler2 = mock(Spieler.class);
		
		when(_spieler1.getName()).thenReturn("name1");
		when(_spieler2.getName()).thenReturn("name2");
		
		_start = mock(Raum.class);
		_ziel = mock(Raum.class);
		
		when(_start.getRaumart()).thenReturn(RaumArt.Start);
		when(_ziel.getRaumart()).thenReturn(RaumArt.Ende);
		
	}

	
	@Test
	public void testRegistriereSpieler() {
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());
		_testLogik.registriereSpieler(_spieler1);
		_testLogik.registriereSpieler(_spieler2);
		assertEquals(2, _testLogik.getKontext().getSpielerListe().size());
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(_spieler1));
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(_spieler2));
	}

	@Test
	public void testMeldeSpielerAb() {
		//registrieren
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());
		_testLogik.registriereSpieler(_spieler1);
		_testLogik.registriereSpieler(_spieler2);
		assertEquals(2, _testLogik.getKontext().getSpielerListe().size());
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(_spieler1));
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(_spieler2));
		//abmelden
		_testLogik.meldeSpielerAb(_spieler1.getName());
		assertEquals(1, _testLogik.getKontext().getSpielerListe().size());
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(_spieler2));
		
		
	
	}

	@Test
	public void testZeigeAktuelleAusgaenge() {
		_testLogik.registriereSpieler(_spieler1);
		
		//Anzahl der Ausgänge abhängig vom Standardlevel 
		//- wird dies geändert schlägt auch der test fehl...
		assertEquals(4, _testLogik.zeigeAktuelleAusgaenge(_spieler1).size());
	}

	@Test
	public void testBeendeSpielSpieler() {
		//nicht implementiert - nicht testbar!!
		//TODO nachziehen wenn impl!
	}

	@Test
	public void testBeendeSpiel() {
		//nicht implementiert - nicht testbar!!
				//TODO nachziehen wenn impl!
	}

	@Test
	public void testIsRaumZielRaum() {
		assertTrue(_testLogik.isRaumZielRaum(_ziel));
		assertFalse(_testLogik.isRaumZielRaum(_start));
	}

	@Test
	public void testGetZielRaum() {
		assertNotNull(_testLogik.getZielRaum());
	}

	@Test
	public void testGetKontext() {
		assertNotNull(_testLogik.getKontext());
	}

	@Test
	public void testGetStruktur() {
		assertNotNull(_testLogik.getStruktur());
	}

	@Test
	public void testRegistriereFeature() {
		TickListener tick =  mock(TickListener.class);
		BefehlAusfuehrenListener befAus = mock(BefehlAusfuehrenListener.class);
		RaumGeaendertListener raumG = mock(RaumGeaendertListener.class);
		BefehlAusgefuehrtListener befAusG = mock(BefehlAusgefuehrtListener.class);
		
		_testLogik.registriereFeature((Feature) tick);
//		_testLogik.registriereFeature((Feature) befAus);
//		_testLogik.registriereFeature((Feature) raumG);
//		_testLogik.registriereFeature((Feature) befAusG);
		
		//TODO weiter
		assertTrue(_testLogik._tickListeners.contains(tick));
//		assertTrue(_testLogik._befehlAusfuehrenListeners.contains(befAus));
//		assertTrue(_testLogik.getKontext().getRaumGeaendertListeners().contains(raumG));
//		assertTrue(_testLogik._befehlAusgefuehrtListeners.contains(befAusG));
		
	}

	@Test
	public void testFuehreTickListenerAus() {
	}

	@Test
	public void testFuehreBefehlAusgefuehrtListenerAus() {
	}

	@Test
	public void testFuehreBefehlAusgefuehrenListenerAus() {
	}

}
