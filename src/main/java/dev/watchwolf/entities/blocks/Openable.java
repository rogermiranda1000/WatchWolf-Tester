package dev.watchwolf.entities.blocks;

/**
 * open
 */
public interface Openable extends BlockModifier {
    boolean isOpened();
    Openable setOpened(boolean opened);

    default Openable open() {
        return this.setOpened(true);
    }
    default Openable close() {
        return this.setOpened(false);
    }
}
