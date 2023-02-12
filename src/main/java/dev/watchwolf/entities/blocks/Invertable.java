package dev.watchwolf.entities.blocks;

public interface Invertable extends BlockModifier {
    boolean isInverted();
    Invertable setInvert(boolean val);
}
