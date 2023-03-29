package dev.watchwolf.entities.blocks;

public interface Powerable extends BlockModifier {
    boolean isPowered();
    Powerable setPowered(boolean val);
}
