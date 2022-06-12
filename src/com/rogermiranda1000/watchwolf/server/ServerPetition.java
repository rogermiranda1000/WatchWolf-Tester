package com.rogermiranda1000.watchwolf.server;

import java.io.IOException;

public interface ServerPetition {
    void opPlayer(String nick) throws IOException;
    void whitelistPlayer(String nick) throws IOException;
    void stopServer(ServerStopNotifier onServerStop) throws IOException;
}
