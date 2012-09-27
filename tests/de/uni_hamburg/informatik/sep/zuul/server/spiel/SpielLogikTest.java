package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.befehle.Befehl;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusfuehrenListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.BefehlAusgefuehrtListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.RaumGeaendertListener;
import de.uni_hamburg.informatik.sep.zuul.server.features.TickListener;
import de.uni_hamburg.informatik.sep.zuul.server.raum.Raum;
import de.uni_hamburg.informatik.sep.zuul.server.raum.RaumArt;
import de.uni_hamburg.informatik.sep.zuul.server.util.ServerKontext;

public class SpielLogikTest
{

	SpielLogik _testLogik = new SpielLogik();
	Spieler _spieler1;
	Spieler _spieler2;
	Raum _start;
	Raum _ziel;

	@Before
	public void setUp()
	{
		_start = mock(Raum.class);
		_ziel = mock(Raum.class);

		when(_start.getRaumart()).thenReturn(RaumArt.Start);
		when(_ziel.getRaumart()).thenReturn(RaumArt.Ende);

	}

	@Test
	public void testRegistriereSpieler()
	{
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());
		_testLogik.erstelleNeuenSpieler("name1");
		_testLogik.erstelleNeuenSpieler("name2");
		assertEquals(2, _testLogik.getKontext().getSpielerListe().size());
	}

	@Test
	public void testMeldeSpielerAb()
	{
		//registrieren
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());
		Spieler s1 = _testLogik.erstelleNeuenSpieler("name1");
		Spieler s2 = _testLogik.erstelleNeuenSpieler("name2");
		assertEquals(2, _testLogik.getKontext().getSpielerListe().size());
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(s1));
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(s2));
		//abmelden
		_testLogik.meldeSpielerAb("name1");
		assertEquals(1, _testLogik.getKontext().getSpielerListe().size());
		assertFalse(_testLogik.getKontext().getSpielerListe().contains(s1));
		assertTrue(_testLogik.getKontext().getSpielerListe().contains(s2));

		_testLogik.meldeSpielerAb("name2");
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());
		assertFalse(_testLogik.getKontext().getSpielerListe().contains(s1));
		assertFalse(_testLogik.getKontext().getSpielerListe().contains(s2));

		_testLogik.meldeSpielerAb("gibts nicht");
		assertEquals(0, _testLogik.getKontext().getSpielerListe().size());

	}

	@Test
	public void testZeigeAktuelleAusgaenge()
	{
		Spieler s1 = _testLogik.erstelleNeuenSpieler("name1");

		//Anzahl der Ausgänge abhängig vom Standardlevel 
		//- wird dies geändert schlägt auch der test fehl...
		assertEquals(4, _testLogik.zeigeAktuelleAusgaenge(s1).size());
	}

	@Test
	public void testBeendeSpielSpieler()
	{
		//nicht implementiert - nicht testbar!!
		//TODO nachziehen wenn impl!
	}

	@Test
	public void testBeendeSpiel()
	{
		//nicht implementiert - nicht testbar!!
		//TODO nachziehen wenn impl!
	}

	@Test
	public void testIsRaumZielRaum()
	{
		assertTrue(_testLogik.isRaumZielRaum(_ziel));
		assertFalse(_testLogik.isRaumZielRaum(_start));
	}

	@Test
	public void testGetZielRaum()
	{
		assertNotNull(_testLogik.getZielRaum());
	}

	@Test
	public void testGetKontext()
	{
		assertNotNull(_testLogik.getKontext());
	}

	@Test
	public void testGetStruktur()
	{
		assertNotNull(_testLogik.getStruktur());
	}

	@Test
	public void testRegistriereFeature()
	{
		TickListener tick = mock(TickListener.class);
		BefehlAusfuehrenListener befAus = mock(BefehlAusfuehrenListener.class);
		RaumGeaendertListener raumG = mock(RaumGeaendertListener.class);
		BefehlAusgefuehrtListener befAusG = mock(BefehlAusgefuehrtListener.class);

		_testLogik.registriereFeature(tick);
		_testLogik.registriereFeature(befAus);
		_testLogik.registriereFeature(raumG);
		_testLogik.registriereFeature(befAusG);

		//TODO weiter
		assertTrue(_testLogik._tickListeners.contains(tick));
		assertTrue(_testLogik._befehlAusfuehrenListeners.contains(befAus));
		assertTrue(_testLogik.getKontext().getRaumGeaendertListeners()
				.contains(raumG));
		assertTrue(_testLogik._befehlAusgefuehrtListeners.contains(befAusG));

	}

	@Test
	public void testFuehreTickListenerAus()
	{
		TickListener tick = mock(TickListener.class);
		_testLogik.registriereFeature(tick);

		_testLogik.fuehreTickListenerAus();

		verify(tick, atLeastOnce()).tick(any(ServerKontext.class));
	}

	@Test
	public void testFuehreBefehlAusgefuehrtListenerAus()
	{
		BefehlAusgefuehrtListener befAusG = mock(BefehlAusgefuehrtListener.class);
		Befehl bef = mock(Befehl.class);

		Spieler s = _testLogik.erstelleNeuenSpieler("name");

		_testLogik.registriereFeature(befAusG);

		_testLogik.fuehreBefehlAusgefuehrtListenerAus(s, bef, true);

		verify(befAusG, atLeastOnce()).befehlAusgefuehrt(
				_testLogik.getKontext(), s, bef, true);

		_testLogik.fuehreBefehlAusgefuehrtListenerAus(s, bef, false);

		verify(befAusG, atLeastOnce()).befehlAusgefuehrt(
				_testLogik.getKontext(), s, bef, false);
	}

	@Test
	public void testFuehreBefehlAusgefuehrenListenerAus()
	{
		BefehlAusfuehrenListener befAus = mock(BefehlAusfuehrenListener.class);
		Befehl bef = mock(Befehl.class);

		Spieler s = _testLogik.erstelleNeuenSpieler("name");

		_testLogik.registriereFeature(befAus);

		_testLogik.fuehreBefehlAusgefuehrenListenerAus(s, bef);

		verify(befAus, atLeastOnce()).befehlSollAusgefuehrtWerden(
				_testLogik.getKontext(), s, bef);

	}

}
