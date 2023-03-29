package dev.watchwolf.entities.files;

import dev.watchwolf.entities.SocketData;
import dev.watchwolf.entities.SocketHelper;

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
        this(name, ConfigFile.getDataFromFile(f), offset);
    }

    public ConfigFile(String name, byte []file, String offset) throws IOException {
        String []nameAndExtension = name.split("\\.(?!.*\\.)"); // split by the last '.'
        nameAndExtension[0] = (nameAndExtension[0].split("\\/(?!.*\\/)").length == 1) ? nameAndExtension[0] : nameAndExtension[0].split("\\/(?!.*\\/)")[1]; // remove folders
        this.name = nameAndExtension[0];
        this.extension = (nameAndExtension.length > 1) ? nameAndExtension[1] : null;
        this.offsetPath = (offset == null || offset.length() == 0) ? "./" : offset;
        if (this.offsetPath.charAt(offsetPath.length() - 1) != '/') this.offsetPath += "/"; // add '/' termination
        this.data = file;
    }

    public ConfigFile(String name, String offset) throws IOException {
        this(name, new File(name), offset);
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

    public ConfigFile(String name) throws IOException {
        this(name, new File(name));
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getOffsetPath() {
        return offsetPath;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addString(out, this.name + ((this.extension != null) ? ("." + this.extension): ""));
        SocketHelper.addString(out, this.offsetPath);

        // add a 4-byte integer
        int fileLength = this.data.length;
        for (int n = 0; n < 4; n++) {
            out.add((byte)(fileLength & 0xFF));
            fileLength >>= 8;
        }

        // add the data
        for (byte b : this.data) out.add(b);
    }
    static {
        SocketData.setReaderFunction(ConfigFile.class, (dis) -> {
            String name = SocketHelper.readString(dis),
                    path = SocketHelper.readString(dis);
            // TODO
            int length = 0;
            return null;
        });
    }
}
