package de.uni_hamburg.informatik.sep.zuul;

/**
 * Informationen über einen vom Benutzer eingegebenen Befehl. Ein Befehl besteht
 * aus zwei Zeichenketten: einem Befehlswort und einem zweiten Wort. Beim Befehl
 * "go west" beispielsweise sind die beiden Zeichenketten "go" und "west". Wenn
 * der Befehl nur aus einem Wort bestand, dann ist das zweite Wort
 * <code>null</code>.
 * 
 * Benutzer dieser Klasse sind dafür zuständig, Befehle auf ihre Gültigkeit zu
 * prüfen. Wenn ein Spieler einen ungültigen Befehl eingegeben hat, sollte das
 * Befehlswort auf <code>null</code> gesetzt werden.
 */
public class Befehl {
    private String befehlswort;
    private String zweitesWort;

    /**
     * Erzeugt ein Befehlsobjekt. Beide Wörter müssen angegeben werden, aber
     * jedes oder beide dürfen 'null' sein. Das Befehlswort sollte 'null' sein,
     * wenn dieser Befehl als nicht vom Spiel erkannt gekennzeichnet werden
     * soll.
     */
    public Befehl(String erstesWort, String zweitesWort) {
        befehlswort = erstesWort;
        this.zweitesWort = zweitesWort;
    }

    /**
     * Liefert das Befehlswort (das erste Wort) dieses Befehls. Wenn der Befehl
     * nicht verstanden wurde, ist das Ergebnis 'null'.
     */
    public String gibBefehlswort() {
        return befehlswort;
    }

    /**
     * Liefert das zweite Wort dieses Befehls. Liefert 'null', wenn es kein
     * zweites Wort gab.
     */
    public String gibZweitesWort() {
        return zweitesWort;
    }

    /**
     * Liefert 'true', wenn dieser Befehl verstanden wurde.
     */
    public boolean istBekannt() {
        return (befehlswort != null);
    }

    /**
     * Liefert 'true', wenn dieser Befehl ein zweites Wort hat.
     */
    public boolean hatZweitesWort() {
        return (zweitesWort != null);
    }
}
