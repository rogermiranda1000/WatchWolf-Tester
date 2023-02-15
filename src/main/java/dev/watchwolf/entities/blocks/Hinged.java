package dev.watchwolf.entities.blocks;

/**
 * hinge
 */
public interface Hinged extends BlockModifier {
    public static enum Hinge {
        LEFT, RIGHT
    }

    Hinged setHinge(Hinge hinge);
    Hinge getHinge();
}
