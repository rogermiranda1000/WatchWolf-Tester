import com.rogermiranda1000.watchwolf.entities.ServerType;
import com.rogermiranda1000.watchwolf.tester.TestConfigFileLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigLoaderTester {
    private static final String PREFIX = "resources";

    @Test
    public void loadSimpleFile() throws IOException {
        TestConfigFileLoader loader = new TestConfigFileLoader(ConfigLoaderTester.PREFIX + "/simple.yaml");

        HashSet<ServerType> expectedServerTypes = new HashSet<>();
        expectedServerTypes.add(ServerType.Spigot);

        HashSet<String> expectedServerVersions = new HashSet<>();
        expectedServerVersions.add("1.14");
        expectedServerVersions.add("1.18.1");

        assertEquals(expectedServerTypes, loader.getServerTypes());
        assertEquals(expectedServerVersions, loader.getServerVersions(ServerType.Spigot));
    }
}
