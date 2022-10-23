package com.rogermiranda1000.watchwolf.entities.blocks;

import com.rogermiranda1000.watchwolf.entities.SocketData;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Block extends SocketData {
    protected final short id;
    protected final String name;

    protected Block(short id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Block(int id, String name) {
        this((short) id, name);
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, this.id);
        SocketHelper.fill(out, 54); // fill 54 bytes of unused arguments
    }

    @Override
    public String toString() {
        // extra variables?
        String variables = Arrays.stream(this.getClass().getDeclaredFields())
                                .filter(f -> f.isAnnotationPresent(RelevantBlockData.class))
                                .map(f -> f.getName()+"="+f.toString())
                                .collect(Collectors.joining(","));

        return this.name + (variables.length() > 0 ? ("{" + variables + "}") : "");
    }
}
