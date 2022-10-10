package com.rogermiranda1000.watchwolf.tester;

public class UnspecifiedConfigFileException extends RuntimeException {
    public UnspecifiedConfigFileException() {
        super("The config file must be specified.");
    }
}
