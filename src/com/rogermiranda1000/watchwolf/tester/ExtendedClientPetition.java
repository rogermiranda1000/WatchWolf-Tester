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
    public Position getPosition() throws IOException;
    public float getPitch() throws IOException;
    public float getYaw() throws IOException;
    public Container getInventory() throws IOException;
}
