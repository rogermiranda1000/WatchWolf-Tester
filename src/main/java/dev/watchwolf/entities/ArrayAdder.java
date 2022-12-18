package dev.watchwolf.entities;

import java.util.ArrayList;

public interface ArrayAdder<T> {
    public void addToArray(ArrayList<Byte> out, T []obj);
}