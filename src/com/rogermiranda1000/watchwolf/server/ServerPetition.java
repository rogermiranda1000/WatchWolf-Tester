package com.rogermiranda1000.watchwolf.server;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.items.Item;

import java.io.IOException;

public interface ServerPetition {
    void opPlayer(String nick) throws IOException;
    void whitelistPlayer(String nick) throws IOException;
    Position getPlayerPosition(String nick) throws IOException;
    void giveItem(String nick, Item item) throws IOException;
    String []getPlayers() throws IOException;
    void stopServer(ServerStopNotifier onServerStop) throws IOException;
    void setBlock(Position position, Block block) throws IOException;
    Block getBlock(Position position) throws IOException;
    void runCommand(String cmd) throws IOException;
}
