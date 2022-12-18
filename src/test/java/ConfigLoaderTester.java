import dev.watchwolf.entities.files.Plugin;
import dev.watchwolf.entities.PluginBuilder;
import dev.watchwolf.entities.ServerType;
import dev.watchwolf.entities.files.UsualPlugin;
import dev.watchwolf.tester.ConfigFileException;
import dev.watchwolf.tester.TestConfigFileLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

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

        ArrayList<String> expectedUsers = new ArrayList<>();
        expectedUsers.add("MinecraftGamer_Z");

        assertEquals(expectedServerTypes, loader.getServerTypes());
        assertEquals(expectedServerVersions, loader.getServerVersions(ServerType.Spigot));
        assertEquals(expectedUsers, Arrays.asList(loader.getUsers()));
        assertEquals(new UsualPlugin("Residence"), loader.getPlugin());
    }

    @Test
    public void loadErrorFile() throws IOException {
        TestConfigFileLoader loader = new TestConfigFileLoader(ConfigLoaderTester.PREFIX + "/error.yaml");

        assertThrowsExactly(ConfigFileException.class, () -> loader.getPlugin()); // a file must contain (at least) the plugin to test
    }

    @Test
    public void loadComplexFile() throws IOException {
        TestConfigFileLoader loader = new TestConfigFileLoader(ConfigLoaderTester.PREFIX + "/complex.yaml");

        HashSet<ServerType> expectedServerTypes = new HashSet<>();
        expectedServerTypes.add(ServerType.Spigot);
        expectedServerTypes.add(ServerType.Paper);

        HashSet<String> expectedSpigotServerVersions = new HashSet<>();
        expectedSpigotServerVersions.add("1.14");
        expectedSpigotServerVersions.add("1.18.1");

        HashSet<String> expectedPaperServerVersions = new HashSet<>();
        expectedPaperServerVersions.add("1.19");

        ArrayList<String> expectedUsers = new ArrayList<>();
        expectedUsers.add("rogermiranda1000");
        expectedUsers.add("MinecraftGamer_Z");

        ArrayList<Plugin> expectedExtraPlugins = new ArrayList<>();
        expectedExtraPlugins.add(PluginBuilder.build("https://watchwolf.dev/versions/WatchWolf-0.1-1.8-1.19.jar"));
        expectedExtraPlugins.add(PluginBuilder.build("./test/" + ConfigLoaderTester.PREFIX + "/Empty.jar"));

        String expectedWorld = "world";

        ArrayList<String> expectedConfigFiles = new ArrayList<>();
        // remember that config-files put the files inside 'plugins/'
        expectedConfigFiles.add("plugins/Config.zip");
        expectedConfigFiles.add("plugins/Test2/Empty.jar");

        assertEquals(expectedServerTypes, loader.getServerTypes());
        assertEquals(expectedSpigotServerVersions, loader.getServerVersions(ServerType.Spigot));
        assertEquals(expectedPaperServerVersions, loader.getServerVersions(ServerType.Paper));
        assertEquals(expectedUsers, Arrays.asList(loader.getUsers()));
        assertEquals(new UsualPlugin("Residence"), loader.getPlugin());
        assertEquals(expectedExtraPlugins, Arrays.asList(loader.getExtraPlugins()));
        assertEquals(1, loader.getMaps().length); assertEquals(expectedWorld, loader.getMaps()[0].getWorldName());
        assertEquals(expectedConfigFiles, Arrays.stream(loader.getConfigFiles()).map(file -> file.getOffsetPath() + file.getName() + "." + file.getExtension()).collect(Collectors.toList()));
    }
}
