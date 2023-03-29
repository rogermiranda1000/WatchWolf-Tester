package dev.watchwolf.serversmanager;

import dev.watchwolf.entities.ServerType;
import dev.watchwolf.entities.files.ConfigFile;
import dev.watchwolf.entities.files.Plugin;

import java.io.IOException;

public interface ServerManagerPetition {
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin[]plugins, ConfigFile[]configFiles) throws IOException;
}
