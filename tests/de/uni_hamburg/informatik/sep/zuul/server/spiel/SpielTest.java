package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.client.ClientPaket;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehlszeile;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;
import de.uni_hamburg.informatik.sep.zuul.server.util.TextVerwalter;

public class SpielTest {
	
	Spiel _testSpiel;
	
	@Before
	public void setUp()
	{
		_testSpiel = new Spiel();
	}

	@Test
	public void testSpiel() {
		assertNotNull(_testSpiel);
		assertFalse(_testSpiel.isGestartet());
	}

	@Test
	public void testMeldeSpielerAn() {
		String name = "name";
		_testSpiel.meldeSpielerAn(name);
		//wenn der Spieler erfolgreich angemeldet wurde, gibt es ihn im System
		//also können wir paekte packen
		assertNotNull(_testSpiel.packePaket(name));
		
		_testSpiel.meldeSpielerAn(name); //es darf keinen Fehler geben
		assertNotNull(_testSpiel.packePaket(name));
	}

	@Test
	public void testMeldeSpielerAb() {
		String name = "name";
		_testSpiel.meldeSpielerAb(name); //es darf keinen fehler geben
		
		_testSpiel.meldeSpielerAn(name);
		//wenn der Spieler erfolgreich angemeldet wurde, gibt es ihn im System
		//also können wir paekte packen
		assertNotNull(_testSpiel.packePaket(name));
		
		_testSpiel.meldeSpielerAb(name);
		//noch nicht testbar!!
		//TODO testen!
		
	}

	@Test
	public void testBeendeSpiel() {
		assertFalse(_testSpiel.isGestartet());
		_testSpiel.spielen();
		assertTrue(_testSpiel.isGestartet());
		_testSpiel.beendeSpiel();
		assertFalse(_testSpiel.isGestartet());
	}

	@Test
	public void testSpielen() {
		assertFalse(_testSpiel.isGestartet());
		_testSpiel.spielen();
		assertTrue(_testSpiel.isGestartet());
	}

	@Test
	public void testVerarbeiteEingabe() {
		//TODO untestbar bei jetziger impl!! 
	}

	@Test
	public void testRestart() {
		assertFalse(_testSpiel.isGestartet());
		_testSpiel.spielen();
		assertTrue(_testSpiel.isGestartet());
		_testSpiel.restart();
		assertTrue(_testSpiel.isGestartet());
	}

	@Test
	public void testPackePaket() {
		String name = "spielername";
		
		_testSpiel.meldeSpielerAn(name);
		ClientPaket paket = _testSpiel.packePaket(name);
		
		assertNotNull(paket);
		//TODO in clientpaket test tun...
//		assertEquals(8, paket.getLebensEnergie());
//		assertEquals(name, paket.getSpielerName());
//		assertEquals(any(Boolean.class), paket.hasKatze());
//		assertEquals(any(Boolean.class), paket.hasMaus());
//		assertEquals(RaumArt.Start, paket.getRaumArt());
//		assertEquals(1, paket.getAndereSpieler().size());
		
		
		
	}

	@Test
	public void testPackeVorschauPaket() {
		String name = "name";
		_testSpiel.meldeSpielerAn(name);
		
		ClientPaket paket = _testSpiel.packeVorschauPaket(name, TextVerwalter.RICHTUNG_NORDEN);
		
		assertNotNull(paket);
		
	}

	@Test
	public void testVersucheBefehlAusfuehrung() {
		Spieler s = mock(Spieler.class);
		ServerKontext kon = mock(ServerKontext.class);
		Befehlszeile zeile = mock(Befehlszeile.class);
		Befehl bef = mock(Befehl.class);
		
		Spiel.versucheBefehlAusfuehrung(kon, s, zeile, bef);
		verify(bef, atLeastOnce()).vorbedingungErfuellt(kon, s, zeile);
		
		when(bef.vorbedingungErfuellt(kon, s, zeile)).thenReturn(true);
		Spiel.versucheBefehlAusfuehrung(kon, s, zeile, bef);
		verify(bef, atLeastOnce()).ausfuehren(kon, s, zeile);
		
		when(bef.vorbedingungErfuellt(kon, s, zeile)).thenReturn(false);
		Spiel.versucheBefehlAusfuehrung(kon, s, zeile, bef);
		verify(bef, atLeastOnce()).gibFehlerAus(kon, s, zeile);
		
	}

	@Test
	public void testIsGestartet() {
		assertFalse(_testSpiel.isGestartet());
		_testSpiel.setGestartet(true);
		assertTrue(_testSpiel.isGestartet());
		
	}

	@Test
	public void testSetGestartet() {
		assertFalse(_testSpiel.isGestartet());
		_testSpiel.setGestartet(true);
		assertTrue(_testSpiel.isGestartet());
		_testSpiel.setGestartet(false);
		assertFalse(_testSpiel.isGestartet());
		
		}

}
