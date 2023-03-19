package world;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WorldLoaderShould.class)
public class WorldLoaderShould extends AbstractTest {
    public static final HashMap<Position, Block> EXPECTED_BLOCKS = new HashMap<>();

    static {
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 65, 180), Blocks.STONE);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 65, 179), Blocks.GRASS_BLOCK);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 66, 178), Blocks.DIRT);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -2, 64, 179), Blocks.STONE);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -2, 65, 178), Blocks.DIRT);

        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 67, 179), Blocks.AIR);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 66, 180), Blocks.AIR);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -3, 66, 181), Blocks.AIR);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -2, 65, 179), Blocks.AIR);
        WorldLoaderShould.EXPECTED_BLOCKS.put(new Position("world", -2, 65, 180), Blocks.AIR);
    }

    @Override
    public String getConfigFile() {
        return "src/test/java/world/resources/config.yaml";
    }

    /**
     * You should be able to send one world and this needs to be loaded.
     * We'll send one 1.8 world (`medium-world.zip`) and try to load it in the same version,
     * both ID-change versions (1.12-1.13), and the last version.
     * @param connector Entry point to the WatchWolf environment
     * @throws Exception The world didn't load
     */
    @ParameterizedTest
    @ArgumentsSource(WorldLoaderShould.class)
    public void loadSentWorld(TesterConnector connector) throws Exception {
        for (Map.Entry<Position,Block> e : WorldLoaderShould.EXPECTED_BLOCKS.entrySet()) {
            assertEquals(e.getValue(), connector.server.getBlock(e.getKey()));
        }
    }
}
