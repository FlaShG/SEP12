package de.uni_hamburg.informatik.sep.zuul.spiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.sep.zuul.xml.RaumSammlungParser;

public class RaumBauer
{
	private Raum _startRaum;

	public RaumBauer(RaumStruktur struktur)
	{
		initialisiereRaeume(struktur.getConnections());
	}

	/**
	 * Verbinde alle Räume.
	 * 
	 * @param verbindungen
	 */
	private void initialisiereRaeume(Map<Raum, Raum[]> verbindungen)
	{
		for(Raum raum : verbindungen.keySet())
		{
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_NORDEN,
					verbindungen.get(raum)[0], TextVerwalter.RICHTUNG_SUEDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_OSTEN,
					verbindungen.get(raum)[1], TextVerwalter.RICHTUNG_WESTEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_SUEDEN,
					verbindungen.get(raum)[2], TextVerwalter.RICHTUNG_NORDEN);
			raum.verbindeZweiRaeume(TextVerwalter.RICHTUNG_WESTEN,
					verbindungen.get(raum)[3], TextVerwalter.RICHTUNG_OSTEN);
		}
	}

	private void initialisiereRaeumeHart()
	{
		// die Räume erzeugen
		// 
		Raum labor = new Raum("Privatlabor",
				"Dr. Little befindet sich in seinem Privatlabor an der Universität.");
		// 
		Raum gang = new Raum(
				"Gang",
				"Dr. Little stolpert in einen dunklen Gang. Die Wände sind mit Büsten von bekannten Unigrößen geschmückt.");
		// Kuuchen?
		Raum mensa = new Raum(
				"Mensa",
				"Dr. Little findet sich in der Mensa wieder. Die Stühle stehen nach dem letzten Mittagessen immer noch im Raum herum.");
		//
		Raum herrentiolette = new Raum(
				"Herrentoilette",
				"Dr. Little betritt die Herrentoilette. Er würde sich gerne die Hände waschen, das Waschbecken ist allerdings viel zu weit oben angebracht.");
		// 
		Raum westfluegel = new Raum(
				"Westflügel",
				"Dr. Little schlendert durch den einsturzgefährdeten Westflügel, dessen morsche Dachbalken wie ein Damoklesschwert über ihm ächtzen.");
		//
		Raum terasse = new Raum(
				"Terrasse",
				"Dr. Little geht auf die Terrasse hinaus. Es sind Möwen zu hören. Zu seiner Rechten sind einige Tische und Gartenstühle zu sehen.");
		// 
		Raum haupteingang = new Raum(
				"Haupteingang",
				"Dr. Little zwängt sich durch die schwere Eingangstür und steht nun vor dem Haupteingang der Universität.");
		// 
		Raum ostfluegel = new Raum(
				"Ostflügel",
				"Dr. Little geht in den Ostflügel. Der Fußboden besteht aus Marmor; er spiegelt sich darin.");
		//
		Raum vorlesungssaal = new Raum(
				"Vorlesungssaal",
				"Dr. Little stolpert in eine Vorlesung. Zum Glück sieht ihn keiner; er sollte sich trotzdem schnell aus dem Staub machen.");
		//
		Raum innenhof = new Raum(
				"Innenhof",
				"Dr. Little betritt den Innenhof. Vor ihm erhebt sich ein großer Brunnen. Eine Windböe wirbelt das Laub auf.");
		//
		Raum sekretariatchemie = new Raum(
				"Sekretariat Chemie",
				"Dr. Little betritt das Chemiesekretariat. Die Frau am Tresen wundert sich, dass sich die Tür öffnet, doch sie kann ihn über den hohen Tresen hinweg nicht sehen.");
		//
		Raum flurchemie = new Raum(
				"Flur Chemie",
				"Dr. Little betritt den Chemieflur. Während er duch den Gang wandert, erschüttert eine Explosion in einem Nebenraum das Gebäude.");
		//
		Raum eingangchemie = new Raum(
				"Eingang Chemie",
				"Dr. Little betritt das Chemiegebäude. Eine Ansammlung von kitteltragenden Wissenschaftlern begräbt ihn fast unter ihren Füßen.");
		//
		Raum flur = new Raum("Flur", "Dr. Little betritt einen Flur.");
		//
		Raum burerohausmeister = new Raum(
				"Hausmeisterbüro",
				"Dr. Little betritt das Büro des Hausmeisters. Es ist niemand da. An der Wand stehen Besen und Putzmittel; der Boden ist mit Schwämmen bedeckt. Die Tür zur Besenkammer steht weit offen.");
		// 
		Raum besenkammerhausmeister = new Raum(
				"Besenkammer",
				"Dr. Little betritt die Besenkammer des Hausmeisters. Es riecht nach Putzmitteln. Ein kleiner Schrank steht leicht geöffnet; es ragen Besenstiele heraus, doch viel zu erkennen ist nicht.");
		//
		Raum bibliothek = new Raum(
				"Bibliothek",
				"Dr. Little betritt die Bibliothek. Ein Haufen Bücher und eine ermahnende Bibliothekarin sind angesichts seiner geringen Größe ein beeindruckender Anblick.");
		//
		Raum wohnung = new Raum(
				"Wohnung",
				"Dr. Little betritt seine Wohnung auf dem Campus. Er wohnt seit Jahren dort, da er ja eh ständig in seinem angrenzenden Labor beschäftigt ist.");
		//
		Raum konferenzraum = new Raum(
				"Konferenzraum",
				"Dr. Little betritt einen Konferenzraum. Einige Geschäftsleute sind gerade dabei ihre Taschen zu packen. Die nächsten stehen bereits vor der Tür.");
		// Even Bigger Labor Ende
		Raum buerovondoktorevenbigger = new Raum(
				"Büro",
				"Dr. Little betritt das Labor seines Kollegen Dr. Evenbigger. Das Ziel ist erreicht.");

		// die Ausgänge initialisieren
		String s = TextVerwalter.RICHTUNG_SUEDEN;
		String n = TextVerwalter.RICHTUNG_NORDEN;
		String e = TextVerwalter.RICHTUNG_OSTEN;
		String w = TextVerwalter.RICHTUNG_WESTEN;

		//_startRaum = labor; // das Spiel startet draussen
		labor.setAusgang(TextVerwalter.RICHTUNG_NORDEN, flur);
		labor.setAusgang(TextVerwalter.RICHTUNG_OSTEN, gang);
		labor.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, wohnung);
		labor.setAusgang(TextVerwalter.RICHTUNG_WESTEN, westfluegel);
		labor.setRaumart(RaumArt.Labor);

		westfluegel.setAusgang(TextVerwalter.RICHTUNG_OSTEN, labor);
		westfluegel
				.setAusgang(TextVerwalter.RICHTUNG_WESTEN, burerohausmeister);

		burerohausmeister.setAusgang(TextVerwalter.RICHTUNG_NORDEN,
				besenkammerhausmeister);
		burerohausmeister.setAusgang(TextVerwalter.RICHTUNG_OSTEN, westfluegel);

		besenkammerhausmeister.setAusgang(TextVerwalter.RICHTUNG_SUEDEN,
				burerohausmeister);
		besenkammerhausmeister.addItem(Item.Kuchen);
		besenkammerhausmeister.addItem(Item.Giftkuchen);
		besenkammerhausmeister.addItem(Item.Kuchen);

		gang.setAusgang(TextVerwalter.RICHTUNG_NORDEN, haupteingang);
		gang.setAusgang(TextVerwalter.RICHTUNG_OSTEN, ostfluegel);
		gang.setAusgang(TextVerwalter.RICHTUNG_WESTEN, labor);
		gang.addItem(Item.Kuchen);

		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_OSTEN, bibliothek);
		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, herrentiolette);
		ostfluegel.setAusgang(TextVerwalter.RICHTUNG_WESTEN, gang);
		ostfluegel.setMaus(new Maus(s));

		bibliothek.setAusgang(TextVerwalter.RICHTUNG_NORDEN, terasse);
		bibliothek.setAusgang(TextVerwalter.RICHTUNG_WESTEN, ostfluegel);

		terasse.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, bibliothek);
		terasse.addItem(Item.Kuchen);
		terasse.addItem(Item.Kuchen);

		flur.setAusgang(TextVerwalter.RICHTUNG_OSTEN, haupteingang);
		flur.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, labor);

		haupteingang.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, gang);
		haupteingang.setAusgang(TextVerwalter.RICHTUNG_WESTEN, flur);

		wohnung.setAusgang(TextVerwalter.RICHTUNG_SUEDEN, konferenzraum);
		wohnung.setAusgang(TextVerwalter.RICHTUNG_NORDEN, labor);
		wohnung.addItem(Item.Kuchen);

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
		sekretariatchemie.addItem(Item.Giftkuchen);

		flurchemie.setAusgang(n, buerovondoktorevenbigger);
		flurchemie.setAusgang(e, sekretariatchemie);

		buerovondoktorevenbigger.setAusgang(s, flurchemie);
		buerovondoktorevenbigger.addItem(Item.Gegengift);

		herrentiolette.setAusgang(n, ostfluegel);
		herrentiolette.setAusgang(s, mensa);

		mensa.setAusgang(n, herrentiolette);
		mensa.setAusgang(w, vorlesungssaal);
		mensa.addItem(Item.Giftkuchen);
		mensa.addItem(Item.Kuchen);

		List<Raum> sammlung = new ArrayList<Raum>();
		sammlung.add(labor);
		sammlung.add(gang);
		sammlung.add(mensa);
		sammlung.add(sekretariatchemie);
		sammlung.add(flur);
		sammlung.add(flurchemie);
		sammlung.add(haupteingang);
		sammlung.add(herrentiolette);
		sammlung.add(besenkammerhausmeister);
		sammlung.add(bibliothek);
		sammlung.add(buerovondoktorevenbigger);
		sammlung.add(burerohausmeister);
		sammlung.add(eingangchemie);
		sammlung.add(westfluegel);
		sammlung.add(ostfluegel);
		sammlung.add(wohnung);
		sammlung.add(konferenzraum);
		sammlung.add(innenhof);
		sammlung.add(terasse);
		sammlung.add(vorlesungssaal);

		RaumStruktur struktur = new RaumStruktur(sammlung);
		_startRaum = labor;
	}

	/**
	 * Gibt den Startraum zurück, von dem aus der Spieler startet.
	 * 
	 * @return Der Startraum
	 */
	public Raum getStartRaum()
	{
		return _startRaum;
	}
}
