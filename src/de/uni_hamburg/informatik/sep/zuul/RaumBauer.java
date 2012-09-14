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
		labor.setzeAusgang("north", flur);
		labor.setzeAusgang("east", gang);
		labor.setzeAusgang("south", wohnung);
		labor.setzeAusgang("west", westfluegel);

		westfluegel.setzeAusgang("east", labor);
		westfluegel.setzeAusgang("west", burerohausmeister);

		burerohausmeister.setzeAusgang("north", besenkammerhausmeister);
		burerohausmeister.setzeAusgang("east", westfluegel);

		besenkammerhausmeister.setzeAusgang("south", burerohausmeister);
		

		gang.setzeAusgang("north", haupteingang);
		gang.setzeAusgang("east", ostfluegel);
		gang.setzeAusgang("west", labor);

		ostfluegel.setzeAusgang("east", bibliothek);
		ostfluegel.setzeAusgang("south", herrentiolette);
		ostfluegel.setzeAusgang("west", gang);

		bibliothek.setzeAusgang("north", terasse);
		bibliothek.setzeAusgang("west", ostfluegel);

		terasse.setzeAusgang("south", bibliothek);

		flur.setzeAusgang("east", haupteingang);
		flur.setzeAusgang("south", labor);

		haupteingang.setzeAusgang("south", gang);
		haupteingang.setzeAusgang("west", flur);

		wohnung.setzeAusgang("south", konferenzraum);
		wohnung.setzeAusgang("north", labor);

		konferenzraum.setzeAusgang(e, vorlesungssaal);
		konferenzraum.setzeAusgang(n, wohnung);

		vorlesungssaal.setzeAusgang(e, mensa);
		vorlesungssaal.setzeAusgang(s, innenhof);
		vorlesungssaal.setzeAusgang(w, konferenzraum);

		innenhof.setzeAusgang(n, vorlesungssaal);
		innenhof.setzeAusgang(s, eingangchemie);

		eingangchemie.setzeAusgang(n, innenhof);
		eingangchemie.setzeAusgang(w, sekretariatchemie);

		sekretariatchemie.setzeAusgang(e, eingangchemie);
		sekretariatchemie.setzeAusgang(w, flurchemie);

		flurchemie.setzeAusgang(n, buerovondoktorevenbigger);
		flurchemie.setzeAusgang(e, sekretariatchemie);

		herrentiolette.setzeAusgang(n, ostfluegel);
		herrentiolette.setzeAusgang(s, mensa);

		mensa.setzeAusgang(n, herrentiolette);
		mensa.setzeAusgang(w, vorlesungssaal);
	}

	public Raum getStartRaum()
	{
		return _startRaum;
	}
}
