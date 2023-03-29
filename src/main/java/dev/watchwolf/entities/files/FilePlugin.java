package dev.watchwolf.entities.files;

import java.util.ArrayList;

public class FilePlugin extends Plugin {
    private final ConfigFile file;

    public FilePlugin(ConfigFile f) {
        this.file = f;
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        out.add((byte) 0x02); // file plugin
        this.file.sendSocketData(out);
    }
}
