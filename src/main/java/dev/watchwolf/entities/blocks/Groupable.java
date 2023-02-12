package dev.watchwolf.entities.blocks;

/**
 * group_count
 */
public interface Groupable extends BlockModifier {
    Ageable setGroupAmount(int amount) throws IllegalArgumentException;
    int getGroupAmount();
    int getMaxGroupAmount();
}
