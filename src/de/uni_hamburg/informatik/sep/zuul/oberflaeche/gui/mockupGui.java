package de.uni_hamburg.informatik.sep.zuul.oberflaeche.gui;

public class mockupGui
{
	public static void main(String[] args)
	{
		ButtonPanel bp = new ButtonPanel(1000);
		EingabePanel ep = new EingabePanel(1000);
		AusgabePanel ap = new AusgabePanel(1000);
		
		Hauptfenster hf = new Hauptfenster(ap, ep, bp);
	}
}
