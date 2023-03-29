package dev.watchwolf.entities.items;

public class ItemNotFoundInContainerException extends RuntimeException {
    public ItemNotFoundInContainerException(String err) {
        super(err);
    }
}
