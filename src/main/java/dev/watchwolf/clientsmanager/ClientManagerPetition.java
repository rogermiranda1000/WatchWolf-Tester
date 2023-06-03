package dev.watchwolf.clientsmanager;

import java.io.IOException;

public interface ClientManagerPetition {
    public String getClientsManagerVersion() throws IOException;
    public String startClient(String username, String serverIp) throws IOException;
}
