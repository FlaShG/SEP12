package de.uni_hamburg.informatik.sep.zuul.server.spiel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;

public class SpielerTest
{
	Spieler _tester;
	Inventar inv = mock(Inventar.class);

	@Before
	public void setUp()
	{
		//standardspieler hat mehr als nur namen.
		_tester = new Spieler("testname", 8, inv);
	}

	@Test
	public void testSpielerString()
	{
		_tester = new Spieler("testname");

		assertEquals("testname", _tester.getName());
	}

	@Test
	public void testSpielerStringIntInventar()
	{
		_tester = new Spieler("testname", 8, inv);

		assertEquals("testname", _tester.getName());
		assertEquals(8, _tester.getLebensEnergie());

		assertEquals(inv, _tester.getInventar());
	}

	@Test
	public void testGetLebensEnergie()
	{
		assertEquals(8, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, _tester.getLebensEnergie());
	}

	@Test
	public void testSetLebensEnergie()
	{
		//selber test wie beim getter.
		assertEquals(8, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, _tester.getLebensEnergie());
		_tester.setLebensEnergie(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, _tester.getLebensEnergie());
	}

	@Test
	public void testGetInventar()
	{
		assertEquals(inv, _tester.getInventar());
		Inventar inv2 = mock(Inventar.class);

		_tester.setInventar(inv2);
		assertEquals(inv2, _tester.getInventar());
	}

	@Test
	public void testSetInventar()
	{
		Inventar inv2 = mock(Inventar.class);
		_tester.setInventar(inv2);
		_tester.setInventar(inv2); //zweimal.. 

		assertEquals(inv2, _tester.getInventar());
	}

	@Test
	public void testSetAktiv()
	{
		_tester.setAktiv(false);
		assertFalse(_tester.getAktiv());

		_tester.setAktiv(true);
		assertTrue(_tester.getAktiv());
	}

	@Test
	public void testGetAktiv()
	{
		_tester.setAktiv(false);
		assertFalse(_tester.getAktiv());

		_tester.setAktiv(true);
		assertTrue(_tester.getAktiv());
	}

	@Test
	public void testGetName()
	{
		assertEquals("testname", _tester.getName());
	}

	@Test
	public void testDie()
	{
		assertTrue(_tester.getLebensEnergie() != 0);

		_tester.die();

		assertEquals(0, _tester.getLebensEnergie());
		assertFalse(_tester.lebendig());
	}

	@Test
	public void testLebendig()
	{
		assertTrue(_tester.lebendig());

		_tester.die();

		assertFalse(_tester.lebendig());
	}

	@Test
	public void testGewinnt()
	{
		assertFalse(_tester.hatGewonnen());

		_tester.gewinnt();

		assertTrue(_tester.hatGewonnen());
	}

	@Test
	public void testHatGewonnen()
	{
		assertFalse(_tester.hatGewonnen());

		_tester.gewinnt();

		assertTrue(_tester.hatGewonnen());
	}

}
