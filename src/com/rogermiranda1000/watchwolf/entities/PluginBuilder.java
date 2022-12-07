package com.rogermiranda1000.watchwolf.entities;

public class PluginBuilder {
    static {
        SocketData.setReaderFunction(Plugin.class, (dis) -> {
            // TODO
            return null;
        });
    }

    public static Plugin build(String path) {
        return null; // TODO
        // TODO check if 'WatchWolf'
    }
}
