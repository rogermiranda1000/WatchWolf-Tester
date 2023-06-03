package dev.watchwolf.tester;

import java.io.IOException;

public interface Petition {
    public String getVersion() throws IOException;
    public void synchronize() throws IOException;
}
