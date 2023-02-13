package dev.watchwolf.entities.blocks;

import java.util.Set;

/**
 * mode
 */
public interface Stateful extends BlockModifier {
    public static enum Mode {
        COMPARE(0), SUBTRACT(1),
        LOAD(0), CORNER(1), SAVE(2);

        private final byte send;
        private Mode(int send) {
            this.send = (byte) send;
        }

        public byte getSendData() {
            return this.send;
        }
    }

    Mode getMode();
    Stateful setMode(Mode mode) throws IllegalArgumentException;
    Set<Mode> getValidModes();
}
