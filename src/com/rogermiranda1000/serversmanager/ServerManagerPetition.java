package com.rogermiranda1000.serversmanager;

import com.rogermiranda1000.entities.ConfigFile;
import com.rogermiranda1000.entities.Map;
import com.rogermiranda1000.entities.Plugin;
import com.rogermiranda1000.entities.ServerType;

import java.io.IOException;

public interface ServerManagerPetition {
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, Map []maps, Plugin []plugins, ServerType mcType, String version, ConfigFile []configFiles) throws IOException;
}
