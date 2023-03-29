package dev.watchwolf.entities.files;

import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

public class UploadedPlugin extends Plugin {
    private final String url;

    public UploadedPlugin(String url) {
        this.url = url;
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        out.add((byte) 0x01); // uploaded plugin
        SocketHelper.addString(out, this.url);
    }
}
