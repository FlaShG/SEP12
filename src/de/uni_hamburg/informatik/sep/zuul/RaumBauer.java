package de.uni_hamburg.informatik.sep.zuul;

public class RaumBauer
{
	private Raum _startRaum;

	public RaumBauer()
	{
		// die Räume erzeugen
		// 
		Raum labor = new Raum(
				"Dr. Little befindet sich in seinem Privatlabor an der Universität.");
		// 
		Raum gang = new Raum("Dr. Little stolpert in einen dunklen Gang. Die Wände sind mit Büsten von bekannten Unigrößen geschmückt.");
		// Kuuchen?
		Raum mensa = new Raum("Dr. Little findet sich in der Mensa wieder. Die Stühle stehen nach dem letzten Mittagessen immer noch im Raum herum.");
		//
		Raum herrentiolette = new Raum("Dr. Little betritt die Herrentoilette. Er würde sich gerne die Hände waschen, das Waschbecken ist allerdings viel zu weit oben angebracht.");
		// 
		Raum westfluegel = new Raum("Dr. Little schlendert durch den einsturzgefährdeten Westflügel, dessen morsche Dachbalken wie ein Damoklesschwert über ihm ächtzen.");
		//
		Raum terasse = new Raum(
				"Dr. Little geht auf die Terrasse hinaus. Es sind Möwen zu hören. Zu seiner Rechten sind einige Tische und Gartenstühle zu sehen.");
		// 
		Raum haupteingang = new Raum(
				"Dr. Little zwängt sich durch die schwere Eingangstür und steht nun vor dem Haupteingang der Universität.");
		// 
		Raum ostfluegel = new Raum(
				"Dr. Little geht in den Ostflügel. Der Fußboden besteht aus Marmor; er spiegelt sich darin.");
		//
		Raum vorlesungssaal = new Raum(
				"Dr. Little stolpert in eine Vorlesung. Zum Glück sieht ihn keiner; er sollte sich trotzdem schnell aus dem Staub machen.");
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
				"Dr. Little betritt das Labor seines Kollegen Dr. Evenbigger. Das Ziel ist erreicht.");

		// die Ausgänge initialisieren
		String s = TextVerwalter.RICHTUNG_SUEDEN;
		String n = TextVerwalter.RICHTUNG_NORDEN;
		String e = TextVerwalter.RICHTUNG_OSTEN;
		String w = TextVerwalter.RICHTUNG_WESTEN;

		_startRaum = labor; // das Spiel startet draussen
		labor.setAusgang(TextVerwalter.RICHTUNG_NORDEN, flur);
		labor.setAusgang(TextVerwalter.RICHTUNG_OSTEN, gang);
		labor.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, wohnung);
		labor.setAusgang(TextVerwalter.RICHTUNG_WESTEN, westfluegel);

		westfluegel.setAusgang(TextVerwalter.RICHTUNG_OSTEN, labor);
		westfluegel.setAusgang(TextVerwalter.RICHTUNG_WESTEN, burerohausmeister);

		burerohausmeister.setAusgang(TextVerwalter.RICHTUNG_NORDEN, besenkammerhausmeister);
		burerohausmeister.setAusgang(TextVerwalter.RICHTUNG_OSTEN, westfluegel);

		besenkammerhausmeister.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, burerohausmeister);
		besenkammerhausmeister.setItem(Item.Kuchen);
		

		gang.setAusgang(TextVerwalter.RICHTUNG_NORDEN, haupteingang);
		gang.setAusgang(TextVerwalter.RICHTUNG_OSTEN, ostfluegel);
		gang.setAusgang(TextVerwalter.RICHTUNG_WESTEN, labor);

		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_OSTEN, bibliothek);
		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, herrentiolette);
		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_WESTEN, gang);

		bibliothek.setAusgang(TextVerwalter.RICHTUNG_NORDEN, terasse);
		bibliothek.setAusgang(TextVerwalter.RICHTUNG_WESTEN, ostfluegel);

		terasse.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, bibliothek);
		terasse.setItem(Item.Kuchen);

		flur.setAusgang(TextVerwalter.RICHTUNG_OSTEN, haupteingang);
		flur.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, labor);

		haupteingang.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, gang);
		haupteingang.setAusgang(TextVerwalter.RICHTUNG_WESTEN, flur);

		wohnung.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, konferenzraum);
		wohnung.setAusgang(TextVerwalter.RICHTUNG_NORDEN, labor);

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

	/**
	 * Gibt den Startraum zurück, von dem aus der Spieler startet.
	 * @return Der Startraum
	 */
	public Raum getStartRaum()
	{
		return _startRaum;
	}
}
