package dev.watchwolf.entities.blocks;

/**
 * rotation
 */
public interface Rotable extends BlockModifier {
    public static enum Rotation {
        S,
        SSW,
        SW,
        WSW,
        W,
        WNW,
        NW,
        NNW,
        N,
        NNE,
        NE,
        ENE,
        E,
        ESE,
        SE,
        SSE
    }

    Rotable setRotation(Rotation rotation);
    Rotation getRotation();
}
