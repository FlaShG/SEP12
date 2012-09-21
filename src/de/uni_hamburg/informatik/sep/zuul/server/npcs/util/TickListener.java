package de.uni_hamburg.informatik.sep.zuul.server.npcs.util;

public interface TickListener
{

	boolean tick(ServerKontext kontext, boolean hasRoomChanged);

}
