package dev.watchwolf.entities.blocks;

import java.util.Set;

/**
 * mode
 */
public interface Stateful extends BlockModifier {
    public static enum Mode {
        COMPARE(0), SUBTRACT(1),
        LOAD(1), CORNER(2), SAVE(3);

        private final byte send;
        private Mode(int send) {
            this.send = (byte) send;
        }

        public byte getSendData() {
            return this.send;
        }
    }

    Stateful getMode();
    Stateful setMode(Stateful mode) throws IllegalArgumentException;
    Set<Stateful> getValidModes();
}
