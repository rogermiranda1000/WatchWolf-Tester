package com.rogermiranda1000.watchwolf.entities.entities;

import com.rogermiranda1000.watchwolf.entities.Position;

public class Entity {
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
}
