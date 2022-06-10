package com.rogermiranda1000.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FilePlugin extends Plugin {
    private final byte []data;

    public FilePlugin(File f) throws IOException {
        FileInputStream fl = new FileInputStream(f);

        this.data = new byte[(int)f.length()];
        fl.read(this.data);

        fl.close();
    }

    public FilePlugin(byte []data) {
        this.data = data;
    }
}
