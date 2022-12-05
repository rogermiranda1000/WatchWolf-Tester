package com.rogermiranda1000.watchwolf.server;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.items.Item;
import com.rogermiranda1000.watchwolf.tester.Petition;

import java.io.IOException;

public interface ServerPetition extends Petition {
    public void opPlayer(String nick) throws IOException;
    public void whitelistPlayer(String nick) throws IOException;
    public Position getPlayerPosition(String nick) throws IOException;
    public float getPlayerPitch(String nick) throws IOException;
    public float getPlayerYaw(String nick) throws IOException;
    public void giveItem(String nick, Item item) throws IOException;
    public void tp(String nick, Position pos) throws IOException;
    public String []getPlayers() throws IOException;
    public void stopServer(ServerStopNotifier onServerStop) throws IOException;
    public void setBlock(Position position, Block block) throws IOException;
    public Block getBlock(Position position) throws IOException;
    public void runCommand(String cmd) throws IOException;
}
