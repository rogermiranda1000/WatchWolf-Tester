package dev.watchwolf.entities.files;

import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

public class UsualPlugin extends Plugin {
    private final String name;
    private final String version;
    private final Boolean isPremium;

    /**
     * A plugin that is already on the ServersManager
     * @param name Plugin name
     * @param version Plugin version (empty string or null if you don't care)
     * @param isPremium If the plugin is premium, or free
     */
    public UsualPlugin(String name, String version, boolean isPremium) {
        this.name = name;
        this.version = version;
        this.isPremium = isPremium;
    }

    public UsualPlugin(String name, String version) {
        this.name = name;
        this.version = version;
        this.isPremium = null;
    }

    public UsualPlugin(String name) {
        this(name, null);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        out.add((byte) 0x00); // usual plugin
        SocketHelper.addString(out, this.name);
        SocketHelper.addString(out, (this.version == null) ? "" : this.version);
    }

    @Override
    public String toString() {
        return "UsualPlugin{" + this.name + (this.version != null ? (" v" + this.version) : "") + "}";
    }
}
