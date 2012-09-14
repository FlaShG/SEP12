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
				"Dr. Little befindet sich in seinem Privatlabor an der Universität.");
		// 
		Raum gang = new Raum("Dr. Little stolpert in einen dunklen Gang. Die Wände sind mit Büsten von bekannten Unigrößen geschmückt.");
		// Kuuchen?
		Raum mensa = new Raum("Dr. Little findet sich in der Mensa wieder. Die Stühle stehen nach dem letzten Mittagessen immer noch im Raum herum.");
		//
		Raum herrentiolette = new Raum("Dr. little betritt die Herrentoilette. Er würde sich gerne die Hände waschen, das Waschbecken ist allerdings viel zu weit oben angebracht.");
		// 
		Raum westfluegel = new Raum("Dr. Little schlendert durch den einsturzgefährdeten Westflügel, dessen morsche Dachbalken wie ein Damoklesschwert über ihm ätchzen.");
		//
		Raum terasse = new Raum(
				"Dr. Little geht auf die Terasse hinaus. Es sind Möwen zu hören. Zu seiner Rechten sind einige Tische und Gartenstühle zu sehen.");
		// 
		Raum haupteingang = new Raum(
				"Dr. Little zwängt sich durch die schwere Eingangstür und steht nun vor dem Haupteingang der Universität.");
		// 
		Raum ostfluegel = new Raum(
				"Dr. Little geht in den Ostflügel. Der Fußboden besteht aus Marmor; er spiegelt sich darin.");
		//
		Raum vorlesungssaal = new Raum(
				"Dr. Little stolpert in eine Vorlesung. Zum Glück sieht ihn keiner; er sollte sich trotzdem schnell aus dem Staub machen");
		//
		Raum innenhof = new Raum("Dr. Little betritt den Innenhof. Vor ihm erhebt sich ein großer Brunnen. Eine Windböe wirbelt das Laub auf.");
		//
		Raum sekretariatchemie = new Raum(
				"Dr. Little betritt das Chemiesekretariat. Die Frau am Tresen wundert sich, dass sich die Tür öffnet, doch sie kann ihn über den hohen Tresen hinweg nicht sehen.");
		//
		Raum flurchemie = new Raum("Dr. Little betritt den Chemieflur. Während er duch den Gang wandert, erschüttert eine Explosion in einem Nebenraum das Gebäude.");
		//
		Raum eingangchemie = new Raum("Dr. Little betritt das Chemiegebäude. Ein Ansammlung von kitteltragenden Wissenschaftlern begräbt ihn fast unter ihren Füßen.");
		//
		Raum flur = new Raum("Dr. Little betritt einen Flur.");
		//
		Raum burerohausmeister = new Raum(
				"Dr. Little betritt das Büro des Hausmeisters. Es ist niemand da. An der Wand stehen Besen und Putzmittel; der Boden ist mit Schwämmen bedeckt. Die Tür zur Besenkammer steht weit offen.");
		// 
		Raum besenkammerhausmeister = new Raum(
				"Dr. Little betritt die Besenkammer des Hausmeisters. Es riecht nach Putzmitteln. Ein kleiner Schrank, offensichtlich der Rest des Mittagessens des Hausmeisters, liegt darin. Es ist dunkel.");
		//
		Raum bibliothek = new Raum("Dr. Little betritt die Bibliothek. Ein Haufen Bücher und eine ermahnende Bibliothekarin sind angesichts seiner geringen Größe ein beeindruckende Anblick.");
		//
		Raum wohnung = new Raum(
				"Dr. Little betritt seine Wohnung auf dem Campus. Er wohnt seit Jahren dort, da er ja eh ständig in seinem angrenzenden Labor beschäftigt ist.");
		//
		Raum konferenzraum = new Raum("Dr. Little betritt einen Konferenzraum. Einige geschäftige Geschäftsleute sind gerade dabei ihre Taschen zu packen. Die nächsten stehen bereits vor der Tür.");
		// Even Bigger Labor Ende
		Raum buerovondoktorevenbigger = new Raum(
				"Dr. Little betritt das Labor seines Konkurrenten Dr. Even Bigger. Das Ziel ist erreicht.");

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
