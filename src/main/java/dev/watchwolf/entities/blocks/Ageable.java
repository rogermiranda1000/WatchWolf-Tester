package dev.watchwolf.entities.blocks;

public interface Ageable {
    int setAge(int age) throws IllegalArgumentException;
    int getAge();
    int getMaxAge();
}
