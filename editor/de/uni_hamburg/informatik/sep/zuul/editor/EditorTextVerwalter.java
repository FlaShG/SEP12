package de.uni_hamburg.informatik.sep.zuul.editor;

import java.util.ArrayList;
import java.util.List;

public final class EditorTextVerwalter
{
	private EditorTextVerwalter()
	{
	}

	public static final String EDITOR_TITEL = "Zuul-Editor";

	public static final List<String> EDITOR_TIPPS;
	static
	{
		EDITOR_TIPPS = new ArrayList<String>();
		EDITOR_TIPPS.add("Tipp: Du kannst Räume per Drag&Drop verschieben.");
		EDITOR_TIPPS
				.add("Tipp: Jedes Level darf nur einen Start- und einen Endraum haben.");
		EDITOR_TIPPS
				.add("Tipp: Lange Raumbeschreibungen schrecken den Spieler ab. Think Twitter.");
		EDITOR_TIPPS
				.add("Tipp: Räume können per Doppelklick hinzugefügt werden.");
		EDITOR_TIPPS
				.add("Tipp: Der Startraum ist immer auch gleichzeitig das Labor.");
		EDITOR_TIPPS
				.add("Tipp: Räume können per Doppelklick hinzugefügt werden.");
		EDITOR_TIPPS.add("Tipp: Starträume sind grün, Endräume rot.");
		EDITOR_TIPPS
				.add("Tipp: Flamingos können nur mit kopfüber gedrehtem Kopf essen.");
	}

	public static final String KLICKE_AUF_RAUM_ZUM_BEGINNEN = "Klicke auf einen Raum, um mit dem editieren zu beginnen.";

	public static final String UNGUELTIGES_LEVEL = "Ungültiges Level";

	public static final String UNGUELTIGES_LEVEL_TEXT = "Level erfüllt nicht die Anforderungen.\nErforderlich sind:\n"
			+ "- Ein Start- und ein Zielraum\n"
			+ "- Ein Weg zwischen Start- und Zielraum\n"
			+ "- Eine unbedenkliche Menge wilder Tiere";

	public static final String LEVEL_LADEN_CHECK = "Möchten Sie wirklich ein anderes Level laden? Ungespeicherte Änderungen werden verloren gehen.";

	public static final String LEVEL_LADEN = "Level laden";

	public static final String NEUES_LEVEL_CHECK = "Möchten Sie wirklich ein neues Level erstellen? Ungespeicherte Änderungen werden verloren gehen.";

	public static final String NEUES_LEVEL = "Neues Level";

	public static final String KARTENGROESSE_AENDERN_CHECK = "Durch diese Änderung werden Räume gelöscht. Wollen Sie wirklich fortfahren?";

	public static final String KARTENGROESSE_AENDERN = "Kartengröße ändern";

	public static final String EDITOR_BEENDEN_CHECK = "Möchten Sie wirklich den Editor beenden? Ungespeicherte Änderungen werden verloren gehen.";

	public static final String EDITOR_BEENDEN = "Editor beenden";
}
