package dev.watchwolf.entities.blocks;

import java.util.Set;

public interface Orientable {
    enum Orientation {
        U, D, N, S, E, W
    }

    boolean isOrientationSet(Orientation o) throws IllegalArgumentException;
    Orientable setOrientation(Orientation o, boolean value) throws IllegalArgumentException;
    Orientable setOrientation(Orientation o) throws IllegalArgumentException;
    Orientable unsetOrientation(Orientation o) throws IllegalArgumentException;
    Set<Orientation> getValidOrientations();
}