package dev.watchwolf.entities.blocks;

import java.util.Set;

public interface Orientable extends BlockModifier {
    public static enum Orientation {
        U, D, N, S, E, W
    }

    public boolean isOrientationSet(Orientation o) throws IllegalArgumentException;
    public Orientable setOrientation(Orientation o, boolean value) throws IllegalArgumentException;
    public Set<Orientation> getValidOrientations();

    public default Orientable setOrientation(Orientation o) throws IllegalArgumentException {
        return this.setOrientation(o, true);
    }

    public default Orientable unsetOrientation(Orientation o) throws IllegalArgumentException {
        return this.setOrientation(o, false);
    }
}