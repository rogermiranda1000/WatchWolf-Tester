package dev.watchwolf.tester;

import java.io.DataInputStream;
import java.io.IOException;

public interface AsyncPetitionResolver {
    public void processAsyncReturn(int header, DataInputStream dis) throws IOException;
}
