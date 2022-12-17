package com.rogermiranda1000.watchwolf.entities.files;

import java.io.File;
import java.io.IOException;

public class WorldFile extends ConfigFile {
    public WorldFile(String name, File f) throws IOException {
        super(name + ".zip", f, name + "/");
    }

    public WorldFile(String name, String f) throws IOException {
        this(name, new File(f));
    }

    public String getWorldName() {
        return this.getName();
    }
}
