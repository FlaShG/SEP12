package de.uni_hamburg.informatik.sep.zuul;

public class RaumBauer
{
	private Raum _startRaum;

	public RaumBauer()
	{
		Raum draussen, hoersaal, cafeteria, labor, buero;

		// die Räume erzeugen
		// 
		labor = new Raum(
				"Dr. Little findet sich in seinem Privatlabor an der Universität");
		// 
		Raum gang = new Raum("Dr. Little stolpert in einen dunklen Gang");
		// Kuuchen?
		Raum mensa = new Raum("Dr. Little findet sich in der Mensa wieder ");
		//
		Raum herrentiolette = new Raum("Dr. little betritt die Herrentoilette");
		// 
		Raum westfluegel = new Raum("Dr. Little betritt den Westflügel");
		//
		Raum terasse = new Raum(
				"Dr. Little geht auf die Terasse hinaus. Es sind Möwen zu hören");
		// 
		Raum haupteingang = new Raum(
				"Dr. Little zwängt sich durch die schwere Eingangstür und steht nun vor dem Haupteingang der Universität");
		// 
		Raum ostfluegel = new Raum(
				"Dr. Little geht in den Ostflügel. Der Fußboden besteht aus Marmor; er spiegelt sich darin");
		//
		Raum vorlesungssaal = new Raum(
				"Dr. Little stolpert in eine Vorlesung. Zum Glück sieht ihn keiner; er sollte sich trotzdem schnell aus dem Staub machen");
		//
		Raum innenhof = new Raum("Dr. Little betritt den Innenhof");
		//
		Raum sekretariatchemie = new Raum(
				"Dr. Little betritt das Chemiesekretariat");
		//
		Raum flurchemie = new Raum("Dr. Little betritt den Chemieflur");
		//
		Raum eingangchemie = new Raum("Dr. Little betritt das Chemiegebäude");
		//
		Raum flur = new Raum("Dr. Little betritt einen Flur");
		//
		Raum burerohausmeister = new Raum(
				"Dr. Little betritt das Büro des Hausmeisters");
		// 
		Raum besenkammerhausmeister = new Raum(
				"Dr. Little betritt die Besenkammer des Hausmeisters");
		//
		Raum bibliothek = new Raum("Dr. Little betritt die Bibliothek");
		//
		Raum wohnung = new Raum(
				"Dr. Little betritt seine Wohnung auf dem Campus");
		//
		Raum konferenzraum = new Raum("Dr. Little betritt einen Konferenzraum");
		// Even Bigger Labor Ende
		Raum buerovondoktorevenbigger = new Raum(
				"Dr. Little betritt das Labor seines Konkurrenten Dr. Even Bigger");

		// die Ausgänge initialisieren
		String s = "south";
		String n = "north";
		String e = "east";
		String w = "west";

		_startRaum = labor; // das Spiel startet draussen
		labor.setAusgang("north", flur);
		labor.setAusgang("east", gang);
		labor.setAusgang("south", wohnung);
		labor.setAusgang("west", westfluegel);

		westfluegel.setAusgang("east", labor);
		westfluegel.setAusgang("west", burerohausmeister);

		burerohausmeister.setAusgang("north", besenkammerhausmeister);
		burerohausmeister.setAusgang("east", westfluegel);

		besenkammerhausmeister.setAusgang("south", burerohausmeister);
		besenkammerhausmeister.setItem(Item.Kuchen);
		

		gang.setAusgang("north", haupteingang);
		gang.setAusgang("east", ostfluegel);
		gang.setAusgang("west", labor);

		ostfluegel.setAusgang("east", bibliothek);
		ostfluegel.setAusgang("south", herrentiolette);
		ostfluegel.setAusgang("west", gang);

		bibliothek.setAusgang("north", terasse);
		bibliothek.setAusgang("west", ostfluegel);

		terasse.setAusgang("south", bibliothek);
		terasse.setItem(Item.Kuchen);

		flur.setAusgang("east", haupteingang);
		flur.setAusgang("south", labor);

		haupteingang.setAusgang("south", gang);
		haupteingang.setAusgang("west", flur);

		wohnung.setAusgang("south", konferenzraum);
		wohnung.setAusgang("north", labor);

		konferenzraum.setAusgang(e, vorlesungssaal);
		konferenzraum.setAusgang(n, wohnung);

		vorlesungssaal.setAusgang(e, mensa);
		vorlesungssaal.setAusgang(s, innenhof);
		vorlesungssaal.setAusgang(w, konferenzraum);

		innenhof.setAusgang(n, vorlesungssaal);
		innenhof.setAusgang(s, eingangchemie);

		eingangchemie.setAusgang(n, innenhof);
		eingangchemie.setAusgang(w, sekretariatchemie);

		sekretariatchemie.setAusgang(e, eingangchemie);
		sekretariatchemie.setAusgang(w, flurchemie);

		flurchemie.setAusgang(n, buerovondoktorevenbigger);
		flurchemie.setAusgang(e, sekretariatchemie);
		
		buerovondoktorevenbigger.setAusgang(s, flurchemie);
		buerovondoktorevenbigger.setItem(Item.Gegengift);

		herrentiolette.setAusgang(n, ostfluegel);
		herrentiolette.setAusgang(s, mensa);

		mensa.setAusgang(n, herrentiolette);
		mensa.setAusgang(w, vorlesungssaal);
		mensa.setItem(Item.Kuchen);
	}

	public Raum getStartRaum()
	{
		return _startRaum;
	}
}
