package de.uni_hamburg.informatik.sep.zuul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Dieser Parser liest Benutzereingaben und wandelt sie in Befehle für das
 * Adventure-Game um. Bei jedem Aufruf liest er eine Zeile von der Konsole und
 * versucht, diese als einen Befehl aus bis zu zwei Wörtern zu interpretieren.
 * Er liefert den Befehl als ein Objekt der Klasse Befehl zurück.
 * 
 * Der Parser verfügt über einen Satz an bekannten Befehlen. Er vergleicht die
 * Eingabe mit diesen Befehlen. Wenn die Eingabe keinen bekannten Befehl
 * enthält, dann liefert der Parser ein als unbekannter Befehl gekennzeichnetes
 * Objekt zurück.
 * 
 */
public class Parser {

    private Befehlswoerter befehle; // hält die gültigen Befehlswörter

    /**
     * Erzeugt einen Parser.
     */
    public Parser() {
        befehle = new Befehlswoerter();
    }

    /**
     * Liest einen Befehl vom Benutzer ein und gibt ihn zurück.
     * 
     * @ensure Ergebnis != null
     */
    public Befehl liefereBefehl() {
        String eingabezeile = ""; // für die gesamte Eingabezeile
        String wort1;
        String wort2;

        System.out.print("> "); // Eingabeaufforderung

        BufferedReader eingabe = new BufferedReader(new InputStreamReader(
                System.in));
        try {
            eingabezeile = eingabe.readLine();
        } catch (IOException exc) {
            System.out.println("There was an error during reading: "
                    + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(eingabezeile);

        if (tokenizer.hasMoreTokens()) {
            wort1 = tokenizer.nextToken(); // erstes Wort
        } else {
            wort1 = null;
        }
        if (tokenizer.hasMoreTokens()) {
            wort2 = tokenizer.nextToken(); // zweites Wort
        } else {
            wort2 = null;
        }

        // Hinweis: Wir ignorieren den Rest der Zeile einfach.

        // Jetzt prüfen, ob der Befehl bekannt ist. Wenn ja, erzeugen
        // wir das passende Befehl-Objekt. Wenn nicht, erzeugen wir
        // einen unbekannten Befehl mit 'null'.

        if (befehle.istBefehl(wort1)) {
            return new Befehl(wort1, wort2);
        }
        return new Befehl(null, wort2);
    }
}
