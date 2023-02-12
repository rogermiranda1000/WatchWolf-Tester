package dev.watchwolf.entities.blocks;

/**
 * leaves
 */
public interface Leaved extends BlockModifier {
    public static enum Leaves {
        NONE, SMALL, LARGE
    }


    Leaves getLeaves();
    Leaved setLeaves(Leaves l);
}
