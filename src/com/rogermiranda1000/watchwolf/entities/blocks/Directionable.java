package com.rogermiranda1000.watchwolf.entities.blocks;

import java.util.Set;

/**
 * Axis
 */
public interface Directionable {
    public static enum Direction {
        X(1), Y(2), Z(3),
        DOUBLE_WALL(2), SINGLE_WALL(1);

        private final byte send;
        private Direction(int send) {
            this.send = (byte) send;
        }

        public byte getSendData() {
            return this.send;
        }
    }

    public boolean isSet(Directionable.Direction d) throws IllegalArgumentException;
    public Orientable set(Directionable.Direction d, boolean value) throws IllegalArgumentException;
    public Orientable set(Directionable.Direction d) throws IllegalArgumentException;
    public Orientable unset(Directionable.Direction d) throws IllegalArgumentException;
    public Set<Directionable.Direction> getValidDirections();
}
