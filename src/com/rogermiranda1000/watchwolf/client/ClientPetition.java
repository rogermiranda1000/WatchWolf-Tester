package com.rogermiranda1000.watchwolf.client;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.items.Item;

import java.io.IOException;

public interface ClientPetition {
    public void sendMessage(String msg) throws IOException;
    public void runCommand(String cmd) throws IOException;
    public void breakBlock(Position block) throws IOException;
    public void equipItemInHand(Item item) throws IOException;
    public void synchronize() throws IOException;
}
