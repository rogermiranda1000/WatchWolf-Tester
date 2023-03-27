package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketData;

public abstract class Entity extends SocketData {
    protected String UUID;
    protected final Position position;

    public Entity(String UUID, Position position) {
        this.UUID = UUID;
        this.position = position;
    }

    public void setUUID(String uuid) {
        this.UUID = uuid;
    }

    /**
     * Used for spawning entities; as they don't exist yet, they don't have an identifier
     * @param position Entity position
     */
    public Entity(Position position) {
        this("", position);
    }

    public String getUUID() {
        return this.UUID;
    }

    public Position getPosition() {
        return this.position;
    }

    public EntityType getType() {
        return EntityType.getType(this);
    }
}
