package com.rogermiranda1000.watchwolf.entities.blocks;

import java.util.Set;

public interface Orientable {
    public static enum Orientation {
        U, D, N, S, E, W
    }

    public boolean isSet(Orientation o) throws IllegalArgumentException;
    public Orientable set(Orientation o, boolean value) throws IllegalArgumentException;
    public Orientable set(Orientation o) throws IllegalArgumentException;
    public Orientable unset(Orientation o) throws IllegalArgumentException;
    public Set<Orientation> getValidOrientations();
}