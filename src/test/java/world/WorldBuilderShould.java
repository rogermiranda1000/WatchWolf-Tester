package world;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.Version;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WorldBuilderShould.class)
public class WorldBuilderShould extends AbstractTest {

    @Override
    public String getConfigFile() {
        return "src/test/java/generic/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(WorldBuilderShould.class)
    public void generateFlatWorldIfNoOptionProvided(TesterConnector connector) throws Exception {

        Position bedrockPosition = new Position("world", 0,getMinBlockInFlatWorld(connector.getServerVersion()),0);

        // TODO what if a village spawns?
        assertEquals(Blocks.BEDROCK, connector.getBlock(bedrockPosition));
        assertEquals(Blocks.DIRT, connector.getBlock(bedrockPosition.add(0,1,0)));
        assertEquals(Blocks.DIRT, connector.getBlock(bedrockPosition.add(0,2,0)));
        assertEquals(Blocks.GRASS_BLOCK, connector.getBlock(bedrockPosition.add(0,3,0)));
        assertEquals(Blocks.AIR, connector.getBlock(bedrockPosition.add(0,4,0)));
    }

    public static int getMinBlockInFlatWorld(String mcVersion) {
        Version versionWithNegativeMinBlock = new Version("1.18"),
                currentVersion = new Version(mcVersion);

        if (currentVersion.compareTo(versionWithNegativeMinBlock) >= 0) return -64;
        return 0;
    }
}
