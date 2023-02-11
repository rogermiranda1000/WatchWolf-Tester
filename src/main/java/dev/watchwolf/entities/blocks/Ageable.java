package dev.watchwolf.entities.blocks;

/**
 * Age
 */
public interface Ageable extends BlockModifier {
    int setAge(int age) throws IllegalArgumentException;
    int getAge();
    int getMaxAge();
}
