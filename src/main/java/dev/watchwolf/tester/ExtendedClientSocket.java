package dev.watchwolf.tester;

import dev.watchwolf.server.ServerPetition;
import dev.watchwolf.entities.Container;
import dev.watchwolf.entities.Position;

import java.io.IOException;
import java.net.Socket;

public class ExtendedClientSocket extends ClientSocket implements ExtendedClientPetition {
    private final ServerPetition server;

    public ExtendedClientSocket(String username, Socket socket, AsyncPetitionResolver asyncResolver, SynchronizationManager syncManager, ServerPetition server) {
        super(username, socket, asyncResolver, syncManager);
        this.server = server;
    }

    @Override
    public Position getPosition() throws IOException {
        return this.server.getPlayerPosition(this.username);
    }

    @Override
    public float getPitch() throws IOException {
        return this.server.getPlayerPitch(this.username);
    }

    @Override
    public float getYaw() throws IOException {
        return this.server.getPlayerYaw(this.username);
    }

    @Override
    public Container getInventory() throws IOException {
        return this.server.getInventory(this.username);
    }
}
