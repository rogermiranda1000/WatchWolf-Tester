package com.rogermiranda1000.watchwolf.entities.blocks;

public class Block {
    protected final short id;
    protected final String name;

    protected Block(short id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Block(int id, String name) {
        this((short) id, name);
    }
}
