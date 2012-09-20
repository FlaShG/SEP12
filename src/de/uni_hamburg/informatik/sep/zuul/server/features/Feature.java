package de.uni_hamburg.informatik.sep.zuul.server.features;

public interface Feature
{
	/**
	 * Mit dieser Methode registriert sich das Feature beim Kontext für Events.
	 * 
	 * @param kontext
	 */
	void registerToKontext(SpielKontext kontext);
}
