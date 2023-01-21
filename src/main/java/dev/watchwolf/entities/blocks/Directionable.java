package dev.watchwolf.entities.blocks;

import java.util.Set;

/**
 * Axis
 */
public interface Directionable {
    enum Direction {
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

    Directionable.Direction getFacingDirection();
    Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException;
    Set<Directionable.Direction> getValidDirections();

}
