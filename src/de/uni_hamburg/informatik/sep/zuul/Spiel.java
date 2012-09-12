package de.uni_hamburg.informatik.sep.zuul;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * Das Ausgangssystem basiert auf einem Beispielprojekt aus dem Buch
 * "Java lernen mit BlueJ" von D. J. Barnes und M. Kölling.
 */
public class Spiel {
    private Parser parser;
    private Raum aktuellerRaum;

    /**
     * Erzeugt ein Spiel und initialisiert die interne Raumkarte.
     */
    public Spiel() {
        legeRaeumeAn();
        parser = new Parser();
    }

    /**
     * Erzeugt alle Räume und verbindet ihre Ausgänge miteinander.
     */
    private void legeRaeumeAn() {
        Raum draussen, hoersaal, cafeteria, labor, buero;

        // die Räume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universität");
        hoersaal = new Raum("in einem Vorlesungssaal");
        cafeteria = new Raum("in der Cafeteria der Uni");
        labor = new Raum("in einem Rechnerraum");
        buero = new Raum("im Verwaltungsbüro der Informatik");

        // die Ausgänge initialisieren
        draussen.setzeAusgang("east", hoersaal);
        draussen.setzeAusgang("south", labor);
        draussen.setzeAusgang("west", cafeteria);
        hoersaal.setzeAusgang("west", draussen);
        cafeteria.setzeAusgang("east", draussen);
        labor.setzeAusgang("north", draussen);
        labor.setzeAusgang("east", buero);
        buero.setzeAusgang("west", labor);

        aktuellerRaum = draussen; // das Spiel startet draussen
    }

    /**
     * Führt das Spiel aus.
     */
    public void spielen() {
        zeigeWillkommenstext();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und führen sie aus, bis das Spiel beendet wird.

        boolean beendet = false;
        while (!beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Gibt einen Begrüßungstext für den Spieler aus.
     */
    private void zeigeWillkommenstext() {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out
                .println("Zuul ist ein neues, unglaublich langweiliges Spiel.");
        System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        zeigeRaumbeschreibung();
    }

    /**
     * Zeigt die Beschreibung des Raums an, in dem der Spieler sich momentan
     * befindet.
     */
    private void zeigeRaumbeschreibung() {
        System.out.println("Sie sind " + aktuellerRaum.gibBeschreibung());
        System.out.print("Ausgänge: ");
        if (aktuellerRaum.gibAusgang("north") != null) {
            System.out.print("north ");
        }
        if (aktuellerRaum.gibAusgang("east") != null) {
            System.out.print("east ");
        }
        if (aktuellerRaum.gibAusgang("south") != null) {
            System.out.print("south ");
        }
        if (aktuellerRaum.gibAusgang("west") != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    /**
     * Verarbeitet einen gegebenen Befehl (führt ihn aus). Wenn der Befehl das
     * Spiel beendet, wird 'true' zurückgeliefert, andernfalls 'false'.
     */
    private boolean verarbeiteBefehl(Befehl befehl) {
        boolean moechteBeenden = false;

        if (!befehl.istBekannt()) {
            System.out.println("Ich weiß nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
        } else if (befehlswort.equals("go")) {
            wechsleRaum(befehl);
        } else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gibt Hilfsinformationen aus.
     */
    private void hilfstextAusgeben() {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigelände herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        System.out.println("   go quit help");
    }

    /**
     * Versucht, den Raum zu wechseln. Wenn es den Ausgang gibt, wird in den
     * Raum gewechselt, sonst wird dem Spieler eine Fehlermeldung angezeigt.
     */
    private void wechsleRaum(Befehl befehl) {
        if (!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin möchten Sie gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);
        if (naechsterRaum == null) {
            System.out.println("Dort ist keine Tür!");
        } else {
            aktuellerRaum = naechsterRaum;
            zeigeRaumbeschreibung();
        }
    }

    /**
     * "quit" wurde eingegeben. Überprüft den Rest des Befehls, ob das Spiel
     * wirklich beendet werden soll. Liefert 'true', wenn der Befehl das Spiel
     * beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) {
        if (befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        return true; // Das Spiel soll beendet werden.
    }

    /**
     * main-Methode zum Ausführen.
     */
    public static void main(String[] args) {
        Spiel spiel = new Spiel();
        spiel.spielen();
    }
}
