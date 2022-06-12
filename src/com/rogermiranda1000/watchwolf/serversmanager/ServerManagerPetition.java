package com.rogermiranda1000.watchwolf.serversmanager;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;

import java.io.IOException;

public interface ServerManagerPetition {
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, Map []maps, Plugin []plugins, ServerType mcType, String version, ConfigFile []configFiles) throws IOException;
}
