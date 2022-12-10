package com.rogermiranda1000.watchwolf.entities.files;

import com.rogermiranda1000.watchwolf.entities.SocketData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigFile extends SocketData {
    private String name;
    private String extension;

    private String offsetPath;

    private byte []data;

    public ConfigFile(String name, File f, String offset) throws IOException {
        String []nameAndExtension = name.split("\\.(?!.*\\.)"); // split by the last '.'
        this.name = nameAndExtension[0];
        this.extension = (nameAndExtension.length > 1) ? nameAndExtension[1] : "";
        this.offsetPath = offset;
        this.data = ConfigFile.getDataFromFile(f);
    }

    private static byte []getDataFromFile(File f) throws IOException {
        FileInputStream fl = new FileInputStream(f);

        byte []r = new byte[(int)f.length()];
        fl.read(r);

        fl.close();
        return r;
    }

    public ConfigFile(String name, File f) throws IOException {
        this(name, f, "./");
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        // TODO
    }
    static {
        SocketData.setReaderFunction(ConfigFile.class, (dis) -> {
            return null; // TODO
        });
    }
}
