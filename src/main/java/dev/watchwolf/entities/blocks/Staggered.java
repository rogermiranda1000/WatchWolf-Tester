package dev.watchwolf.entities.blocks;

public interface Staggered extends BlockModifier {
    int getStage();
    Staggered setStage(int stage) throws IllegalArgumentException;
    int getMaxStage();
    int getMinStage();
}
