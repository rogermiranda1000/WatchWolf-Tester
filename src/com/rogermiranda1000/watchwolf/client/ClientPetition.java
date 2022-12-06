package com.rogermiranda1000.watchwolf.client;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.items.Item;
import com.rogermiranda1000.watchwolf.tester.Petition;

import java.io.IOException;

public interface ClientPetition extends Petition {
    public void sendMessage(String msg) throws IOException;
    public void runCommand(String cmd) throws IOException;
    public void breakBlock(Position block) throws IOException;
    public void equipItemInHand(Item item) throws IOException;
    public void moveTo(Position pos) throws IOException;
    public void lookAt(float pitch, float yaw) throws IOException;
    public void hit() throws IOException;
    public void use() throws IOException;
}
