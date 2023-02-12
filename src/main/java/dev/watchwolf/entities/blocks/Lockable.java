package dev.watchwolf.entities.blocks;

/**
 * repeater locked
 */
public interface Lockable extends BlockModifier {
    boolean isLocked();
    Lockable setLocked(boolean val);

    default Lockable lock() {
        return this.setLocked(true);
    }

    default Lockable unlock() {
        return this.setLocked(false);
    }
}
