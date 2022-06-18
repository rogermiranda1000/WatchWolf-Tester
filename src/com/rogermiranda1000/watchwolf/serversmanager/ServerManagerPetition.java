package com.rogermiranda1000.watchwolf.serversmanager;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;

import java.io.IOException;

public interface ServerManagerPetition {
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin []plugins, Map []maps, ConfigFile []configFiles) throws IOException;
}
