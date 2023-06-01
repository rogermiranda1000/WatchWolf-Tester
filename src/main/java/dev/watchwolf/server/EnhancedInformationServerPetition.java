package dev.watchwolf.server;

import dev.watchwolf.entities.files.ConfigFile;

import java.io.IOException;

public interface EnhancedInformationServerPetition {
    void startTimings() throws IOException;
    ConfigFile stopTimings() throws IOException;
}
