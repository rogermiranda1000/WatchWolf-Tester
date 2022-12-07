package com.rogermiranda1000.watchwolf.tester;

public class UnspecifiedConfigFileException extends RuntimeException {
    public UnspecifiedConfigFileException(String msg) {
        super(msg);
    }

    public UnspecifiedConfigFileException(Throwable ex) {
        super("The config file was specified, but an error occurred while reading it.", ex);
    }
}
