package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.entities.Map;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;

/**
 * All AbstractTest's instances will have one of those objects to get the necessary data in order to run the tests.
 * The problem is that only `beforeAll` needs the information, and JUnit creates multiple instances to fulfill the tests,
 * that's why we won't load any part of the file until it is explicitly requested.
 */
public class TestConfigFileLoader {
    private final InputStream inputStream;
    private HashMap<String,Object> loadedYaml;

    /**
     * Server type, with all its versions
     */
    private HashMap<ServerType,Set<String>> serverType;
    private Boolean overrideSync;
    private Plugin plugin;
    private Set<Plugin> extraPlugins;
    private Set<Map> maps;
    private Set<ConfigFile> configFiles;
    private Set<String> users;

    public TestConfigFileLoader(String file) throws IOException {
        this.inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(file);
        if (this.inputStream == null) throw new IOException("Couldn't open InputStream at " + file);
    }

    private <T> T getEntry(Function<HashMap<String,Object>,T> getter) {
        Yaml yaml = new Yaml();
        if (this.loadedYaml == null) this.loadedYaml = yaml.load(this.inputStream);
        if (this.loadedYaml == null) return null; // empty file
        try {
            return getter.apply(this.loadedYaml);
        } catch (NullPointerException ignore) {
            return null;
        }
    }

    public Set<ServerType> getServerTypes() throws IllegalArgumentException {
        if (this.serverType == null) {
            this.serverType = new HashMap<>();

            Set<ServerType> r = this.getEntry(it -> {
                ArrayList<LinkedHashMap<String,Object>> types = (ArrayList<LinkedHashMap<String,Object>>) it.get("server-type");
                final Set<ServerType> re = new HashSet<>();
                for (LinkedHashMap<String,Object> e : types) {
                    e.keySet().stream().map(type -> ServerType.valueOf(type)).forEach(s -> re.add(s));
                }
                return re;
            });
            if (r == null) return null; // not found
            for (ServerType type : r) this.serverType.put(type, null);
        }

        return this.serverType.keySet();
    }

    /**
     * Get the versions that will be launched for that server type.
     * The first time it will get loaded.
     * @param serverType Type of server to get the versions
     * @return The versions of that server type; null if no server versions for that type.
     */
    public Set<String> getServerVersions(final ServerType serverType) {
        if (this.serverType == null) this.getServerTypes(); // first load hashmap
        Set<String> versions = this.serverType.get(serverType);
        if (versions == null) {
            Set<String> r = this.getEntry(it -> {
                ArrayList<LinkedHashMap<String,ArrayList<String>>> types = (ArrayList<LinkedHashMap<String,ArrayList<String>>>) it.get("server-type");
                final Set<String> re = new HashSet<>();
                for (LinkedHashMap<String,ArrayList<String>> e : types) {
                    ArrayList<String> wantedElements = e.get(serverType.name());
                    if (wantedElements != null) re.addAll(wantedElements);
                }
                return re;
            });

            if (r == null || r.size() < 1) return null; // not found / not enough versions for the specified type
            versions = r;
            this.serverType.put(serverType, versions);
        }

        return new HashSet<>(versions);
    }

    public boolean getOverrideSync() {
        if (this.overrideSync == null) {
            this.overrideSync = false; // TODO
        }

        return this.overrideSync;
    }

    public Plugin getPlugin() throws UnspecifiedConfigFileException {
        if (this.plugin == null) {
            String r = this.getEntry(it -> (String) it.get("plugin"));
            if (r == null) throw new UnspecifiedConfigFileException("The config file must contain a plugin to test.");
            this.plugin = PluginBuilder.build(r);
        }

        return this.plugin;
    }

    public Plugin []getExtraPlugins() {
        if (this.extraPlugins == null) {
            this.extraPlugins = new HashSet<>();
            ArrayList<String> r = this.getEntry(it -> (ArrayList<String>) it.get("extra-plugins"));
            if (r != null) { // maybe any extra plugin?
                for (String path : r) this.extraPlugins.add(PluginBuilder.build(path));
            }
        }

        return this.extraPlugins.toArray(new Plugin[0]);
    }

    public String []getUsers() {
        if (this.users == null) {
            this.users = new HashSet<>();
            this.users.add("rogermiranda1000"); // TODO
        }

        return this.users.toArray(new String[0]);
    }

    public Map []getMaps() {
        if (this.maps == null) {
            this.maps = new HashSet<>();
            // TODO
        }

        return this.maps.toArray(new Map[0]);
    }

    public ConfigFile []getConfigFiles() {
        if (this.configFiles == null) {
            this.configFiles = new HashSet<>();
            // TODO
        }

        return this.configFiles.toArray(new ConfigFile[0]);
    }
}
