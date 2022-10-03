package com.rogermiranda1000.watchwolf.server;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;

import java.io.IOException;

public interface ServerPetition {
    void opPlayer(String nick) throws IOException;
    void whitelistPlayer(String nick) throws IOException;
    void stopServer(ServerStopNotifier onServerStop) throws IOException;
    void setBlock(Position position, Block block) throws IOException;
    Block getBlock(Position position) throws IOException;
}
