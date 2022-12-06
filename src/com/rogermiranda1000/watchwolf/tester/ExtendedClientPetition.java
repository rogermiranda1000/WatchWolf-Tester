package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.entities.Container;
import com.rogermiranda1000.watchwolf.entities.Position;

import java.io.IOException;

/**
 * There's some petitions player-related that needs to be launched in the server-side.
 * The interface will "redirect" the petition, thus being invisible to the user.
 */
public interface ExtendedClientPetition extends ClientPetition {
    public Position getPlayerPosition(String nick) throws IOException;
    public float getPlayerPitch(String nick) throws IOException;
    public float getPlayerYaw(String nick) throws IOException;
    public Container getInventory(String nick) throws IOException;
}
