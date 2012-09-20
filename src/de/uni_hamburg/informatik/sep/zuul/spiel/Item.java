package de.uni_hamburg.informatik.sep.zuul.spiel;

/**
 * Definiert moegliche Items die im Spiel verfuegbar sind.
 * 
 * @author 0gayh, 0ortmann
 * 
 */
public enum Item
{
	UKuchen("Krümel"), IKuchen("Guter Krümel"), UGiftkuchen("Krümel"), IGiftkuchen(
			"Schlechter Krümel"), Gegengift("Gegengift"), Keins("");

	private final String _text;

	Item(String text)
	{
		_text = text;
	}

	@Override
	public String toString()
	{
		return _text;
	}

}
