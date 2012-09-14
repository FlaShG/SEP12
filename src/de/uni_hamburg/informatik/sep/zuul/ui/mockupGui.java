package de.uni_hamburg.informatik.sep.zuul.ui;

public class mockupGui
{
	public static void main(String[] args)
	{
		ButtonPanel bp = new ButtonPanel(1024);
		EingabePanel ep = new EingabePanel(1024);
		AusgabePanel ap = new AusgabePanel(1024);
		
		Hauptfenster hf = new Hauptfenster(ap, ep, bp);
	}
}
