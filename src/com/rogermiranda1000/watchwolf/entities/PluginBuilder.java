package com.rogermiranda1000.watchwolf.entities;

import java.io.File;
import java.io.IOException;

public class PluginBuilder {
    static {
        SocketData.setReaderFunction(Plugin.class, (dis) -> {
            // TODO
            return null;
        });
    }

    public static Plugin build(String path) throws IOException {
        if (path.startsWith("https://") || path.startsWith("http://")) return new UploadedPlugin(path);
        else if (path.contains("/")) return new FilePlugin(new File(path));
        else return new UsualPlugin(path); // TODO version with the path
    }
}
