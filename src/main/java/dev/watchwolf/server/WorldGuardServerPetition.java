package dev.watchwolf.server;

import dev.watchwolf.entities.Position;

import java.io.IOException;
import java.util.List;

public interface WorldGuardServerPetition {
    void createRegion(String name, Position firstCoordinate, Position secondCoordinate) throws IOException;
    String []getRegions() throws IOException;
    String []getRegions(Position pos) throws IOException;
}
