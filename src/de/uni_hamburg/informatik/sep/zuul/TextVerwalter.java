package de.uni_hamburg.informatik.sep.zuul;


/**
 * Zentraler Speicher der Ausgabetexte, sowie der Befehls- und Richtungstexte.
 * 
 * @author 0klein, 1jost
 *
 */
public class TextVerwalter
{
	public static final String EINLEITUNGSTEXT = "Willkommen zu Zuul, dem nicht mehr ganz so langweiligen Abenteurspiel mit Dr. Little.\n\nNach einem fatalen Unfall in seinem Chemielabor ist der Wissenschaftler Dr. Little dazu verdammt, in geringer Körpergröße eine Lösung seines Dilemmas zu finden. Er weiß, dass sein Kollege Prof. Dr. Evenbigger bei seinen Forschungen über das Verhalten von Sonnenblumen unter Aussetzung von Beta-Strahlung zufälligerweise auf ein Mittel gestoßen ist, mithilfe dessen Dr. Little seine normale Größe zurückerlangen könnte. Prof. Dr. Evenbiggers Labor befindet sich jedoch auf der anderen Seite des Universitätscampus und in Dr. Littles aktueller körperlicher Verfassung stellt die Reise eine große Herausforderung für ihn dar. Er muss sich nun mit seiner mäßigen Orientierung (er brauchte sich vor dem Unfall nicht sehr um das restliche Unigelände kümmern) das Labor des Professors finden. Ihnen obliegt die Verantwortung, Dr. Little sicher durch das Labyrinth von Räumen zu seinem Ziel zu bringen. Dabei besteht aber noch ein Problem: Dr. Little leidet stark unter dem Einfluss seines fehlgeschlagenen Experiments und die körperliche Anstrengung des Durchreisens der Räume setzt so zu, dass er nach 8 Räumen ohne Nahrungsaufnahme den qualvollen Tod erwarten muss. Doch zum Glück haben unachtsame Studenten Kuchenstücke in verschiedenen Teilen des Campus liegengelassen. Wenn es Dr. Little gelingt, eines davon zu erwischen, so verbessert sich sein Gesundheitszustand, sodass er drei weitere Räume durchqueren kann. So starten Sie nun furchtlos in ein spannendes Abenteuer und retten Sie den Doktor vor seinem Verderben.";
	public static final String SIEGTEXT = "Gute Arbeit. Sie haben Dr. Little erfolgreich an sein Ziel gebracht. Sein Kollege Prof. Dr. Evenbigger verabreicht ihm nun das Gegenmittel und verhilft ihm wieder zu seiner vollen Größe.";

	public static final String KUCHENGEFUNDENTEXT = "Dr. Little findet ein Stück Kuchen. Er isst es und füllt seine Kraftreserven um 3 weitere Räume auf.\nEr kann noch folgende Anzahl an Räumen durchschreiten: ";

	public static final String NIEDERLAGETEXT = "Dr. Little ist vor Erschöpfung und Hunger zusammengebrochen. Starte erneut.";
	public static final String BEENDENTEXT = "Dr. Little dankt Ihnen für Ihre Hilfe bei seinem aufregenden Abenteuer. Bis zum nächsten Mal bei Zuul.";
	public static final String HILFETEXT = "Sie haben die Hilfe aufgerufen. Ihr Ziel ist es, in möglichst wenigen Schritten das Labor von Prof. Dr. Evenbigger zu finden. Sie können nur eine bestimmte Anzahl an Räumen durchqueren. Wenn Sie den Raum wechseln, verringert sich diese Anzahl um einen Raum. Mit Kuchen kann diese Anzahl aber wieder erhöht werden. Um über die Texteingabe zu interagieren, stehen Ihnen folgende Befehle zur Verfügung: ";
	public static final String RAUMWECHSELTEXT = "Der Doktor kann nun einen Raum weniger betreten. Er kann noch folgende Anzahl an Räumen betreten: ";
	public static final String FALSCHEEINGABE = "Ich weiß nicht, was Sie meinen...";
	
	public static final String KEINERICHTUNG = "Wohin möchten Sie gehen?";
	public static final String KEINETUER = "Dort ist keine Tür. Wählen Sie eine andere Richtung.";
	public static final String AUSGAENGE = "Ausgänge";
	
	public static final String BEFEHL_GEHEN = "go";
	public static final String BEFEHL_HILFE = "help";
	public static final String BEFEHL_BEENDEN = "quit";

	public static final String RICHTUNG_NORDEN = "north";
	public static final String RICHTUNG_SUEDEN = "south";
	public static final String RICHTUNG_WESTEN = "west";
	public static final String RICHTUNG_OSTEN = "east";
	
	public static final String MAUS_GEFUNDEN = "Dr. Little hat eine Maus gefunden.";
	public static final String MAUS_FRAGE = "Möchten Sie der Maus einen Krümel geben, damit sie Dr. Little den Weg weist?";
	public static final String MAUS_RICHTUNGSANGABE = "Maus: 'Ich vermute, dass %s die richtige Richtung ist.'";
	public static final String MAUS_KEIN_KRUEMEL = "Dr. Little hat keinen Krümel, den er abgeben könnte...";
	public static final String MAUS_KEINE_MAUS = "Hier ist niemand, dem Dr. Little einen Krümel geben könnte...";
}
