package dev.watchwolf.tester;

public class UnspecifiedConfigFileException extends ConfigFileException {
    public UnspecifiedConfigFileException() {
        super("The config file must be specified.");
    }
}
