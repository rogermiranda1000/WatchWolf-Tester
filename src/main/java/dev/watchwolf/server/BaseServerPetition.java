package dev.watchwolf.server;

import dev.watchwolf.entities.Container;
import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.items.Item;
import dev.watchwolf.tester.Petition;

import java.io.IOException;

public interface BaseServerPetition extends Petition {
    public void opPlayer(String nick) throws IOException;
    public void whitelistPlayer(String nick) throws IOException;
    public Position getPlayerPosition(String nick) throws IOException;
    public float getPlayerPitch(String nick) throws IOException;
    public float getPlayerYaw(String nick) throws IOException;
    public void giveItem(String nick, Item item) throws IOException;
    public void tp(String nick, Position pos) throws IOException;
    public Container getInventory(String nick) throws IOException;
    public String []getPlayers() throws IOException;
    public void stopServer(ServerStopNotifier onServerStop) throws IOException;
    public void setBlock(Position position, Block block) throws IOException;
    public Block getBlock(Position position) throws IOException;
    public String runCommand(String cmd) throws IOException;
    public Entity[]getEntities(Position center, double radius) throws IOException;
    public Entity spawnEntity(Entity e) throws IOException;
    public Entity getEntity(String UUID) throws IOException;
}
