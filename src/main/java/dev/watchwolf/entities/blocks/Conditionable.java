package dev.watchwolf.entities.blocks;

/**
 * conditional
 */
public interface Conditionable extends BlockModifier {
    boolean isConditional();
    Conditionable setConditional(boolean val);
}
