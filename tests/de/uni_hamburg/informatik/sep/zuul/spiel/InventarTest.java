package de.uni_hamburg.informatik.sep.zuul.spiel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.sep.zuul.server.inventar.Inventar;
import de.uni_hamburg.informatik.sep.zuul.server.inventar.Item;

public class InventarTest
{

	@Test
	public void testInventar()
	{
		Inventar inv = new Inventar();
		assertNotNull(inv.getInhaltsliste());
	}

	@Test
	public void testFuegeItemHinzu()
	{
		Inventar inv = new Inventar();
		Item it = Item.UKuchen;

		inv.fuegeItemHinzu(Item.Keins);
		assertFalse(inv.isGefuellt());

		inv.fuegeItemHinzu(it);

		assertTrue(inv.isGefuellt());
		assertEquals(it, inv.nehmeLetztesItem());
	}

	@Test
	public void testNehmeLetztesItem()
	{
		Inventar inv = new Inventar();
		Item it = Item.UKuchen;

		inv.fuegeItemHinzu(it);

		assertEquals(it, inv.nehmeLetztesItem());
		assertFalse(inv.isGefuellt());

		assertEquals(Item.Keins, inv.nehmeLetztesItem());
	}

	@Test
	public void testIsGefuellt()
	{
		Inventar inv = new Inventar();
		assertFalse(inv.isGefuellt());

		inv.fuegeItemHinzu(Item.UKuchen);
		assertTrue(inv.isGefuellt());
	}

	@Test
	public void testHasAnyKuchen()
	{
		Inventar neuesInv = new Inventar();
		assertFalse(neuesInv.hasAnyKuchen());

		neuesInv.fuegeItemHinzu(Item.UKuchen);

		assertTrue(neuesInv.hasAnyKuchen());
	}

	@Test
	public void testGetAnyKuchen()
	{
		Inventar neuesInv = new Inventar();
		assertFalse(neuesInv.hasAnyKuchen());

		neuesInv.fuegeItemHinzu(Item.UKuchen);

		assertTrue(neuesInv.hasAnyKuchen());

		neuesInv.getAnyKuchen();

		assertFalse(neuesInv.hasAnyKuchen());
	}

}
