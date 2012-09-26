package de.uni_hamburg.informatik.sep.zuul.server.inventar;

/**
 * Definiert moegliche Items die im Spiel verfuegbar sind.
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public enum Item
{
	UKuchen("Krümel"), IKuchen("Guter Krümel"), UGiftkuchen("Krümel"), IGiftkuchen(
			"Schlechter Krümel"), Gegengift("Gegengift"), Keins("Nichts");

	private final String _name;

	Item(String name)
	{
		_name = name;
	}

	public String toString()
	{
		return _name;
	}

	public boolean isAnyKuchen()
	{
		return isIKuchen() || isUKuchen();
	}

	public boolean isIKuchen()
	{
		return this == IKuchen || this == IGiftkuchen;
	}

	public boolean isUKuchen()
	{
		return this == UKuchen || this == UGiftkuchen;
	}

	public boolean isKuchen()
	{
		return this == IKuchen || this == UKuchen;
	}

	public boolean isGiftkuchen()
	{
		return this == IGiftkuchen || this == UGiftkuchen;
	}

	/**
	 * @param item
	 * @return
	 */
	public static Item convertIToUKuchen(Item item)
	{
		if(item == IKuchen)
			return UKuchen;
		if(item == IGiftkuchen)
			return UGiftkuchen;
		return item;
	}

}
