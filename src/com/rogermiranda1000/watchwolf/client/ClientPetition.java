package com.rogermiranda1000.watchwolf.client;

import java.io.IOException;

public interface ClientPetition {
    public void sendMessage(String msg) throws IOException;
    public void runCommand(String cmd) throws IOException;
}
