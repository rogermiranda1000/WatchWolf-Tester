package com.rogermiranda1000.watchwolf.entities.blocks;

import java.util.Set;

/**
 * Axis
 */
public interface Directionable {
    public static enum Direction {
        NONE(0),
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

    public Directionable.Direction getFacingDirection();
    public Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException;
    public Set<Directionable.Direction> getValidDirections();
}
