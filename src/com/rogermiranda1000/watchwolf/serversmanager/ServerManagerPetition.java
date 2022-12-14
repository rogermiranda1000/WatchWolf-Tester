package com.rogermiranda1000.watchwolf.serversmanager;

import com.rogermiranda1000.watchwolf.entities.files.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.files.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;

import java.io.IOException;

public interface ServerManagerPetition {
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin []plugins, ConfigFile []configFiles) throws IOException;
}
