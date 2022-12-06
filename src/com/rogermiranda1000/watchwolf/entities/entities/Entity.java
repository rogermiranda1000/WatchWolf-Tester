package com.rogermiranda1000.watchwolf.entities.entities;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.SocketData;

import java.util.ArrayList;

public class Entity extends SocketData {
    private final String UUID;
    private final Position position;

    public Entity(String UUID, Position position) {
        this.UUID = UUID;
        this.position = position;
    }

    public String getUUID() {
        return this.UUID;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        // TODO
    }
}
