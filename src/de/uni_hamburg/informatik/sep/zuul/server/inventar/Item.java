package de.uni_hamburg.informatik.sep.zuul.server.inventar;

/**
 * Definiert moegliche Items die im Spiel verfuegbar sind.
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public enum Item
{
	UKuchen("Krümel"), IKuchen("Guter Krümel"), UGiftkuchen("Krümel"), IGiftkuchen("schlechter Krümel"), Gegengift("Gegengift"), Keins("Nichts");
	
	private final String _name;
	
	Item(String name)
	{
		_name = name;
	}
	
	public String toString()
	{
		return _name;
	}
}
