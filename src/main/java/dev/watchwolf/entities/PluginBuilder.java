package dev.watchwolf.entities;

import dev.watchwolf.entities.files.*;

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
        else if (path.contains("/")) return new FilePlugin(new ConfigFile(path));
        else return new UsualPlugin(path); // TODO version with the path
    }
}
