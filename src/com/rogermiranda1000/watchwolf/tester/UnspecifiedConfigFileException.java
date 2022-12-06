package com.rogermiranda1000.watchwolf.tester;

public class UnspecifiedConfigFileException extends RuntimeException {
    public UnspecifiedConfigFileException() {
        super("The config file must be specified.");
    }

    public UnspecifiedConfigFileException(Throwable ex) {
        super("The config file was specified, but an error occurred while reading it.", ex);
    }
}
