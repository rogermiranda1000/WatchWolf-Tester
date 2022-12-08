package com.rogermiranda1000.watchwolf.tester;

public class ConfigFileException extends RuntimeException {
    public ConfigFileException(String msg) {
        super(msg);
    }

    public ConfigFileException(Throwable ex) {
        super("An error occurred while processing the config file.", ex);
    }
}
