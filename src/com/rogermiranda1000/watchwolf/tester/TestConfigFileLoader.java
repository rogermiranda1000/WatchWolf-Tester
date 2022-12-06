package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * All AbstractTest's instances will have one of those objects to get the necessary data in order to run the tests.
 * The problem is that only `beforeAll` needs the information, and JUnit creates multiple instances to fulfill the tests,
 * that's why we won't load any part of the file until it is explicitly requested.
 */
public class TestConfigFileLoader {
    private final InputStream inputStream;

    /**
     * Server type, with all its versions
     */
    private HashMap<ServerType,Set<String>> serverType;
    private Boolean overrideSync;
    private Set<Plugin> plugins;
    private Set<Map> maps;
    private Set<ConfigFile> configFiles;
    private Set<String> users;

    public TestConfigFileLoader(String file) throws IOException {
        this.inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(file);
        if (this.inputStream == null) throw new IOException("Couldn't open InputStream at " + file);
    }

    private Object getEntry() {
        Yaml yaml = new Yaml();
        HashMap<String,Object> obj = yaml.load(this.inputStream);
        System.out.println(obj.toString());
        return null;
    }

    public Set<ServerType> getServerTypes() {
        if (this.serverType == null) {
            this.serverType = new HashMap<>();

            this.getEntry();
            this.serverType.put(ServerType.Spigot, null); // TODO
        }

        return this.serverType.keySet();
    }

    /**
     * Get the versions that will be launched for that server type.
     * The first time it will get loaded.
     * @param serverType Type of server to get the versions
     * @return The versions of that server type; null if no server versions for that type.
     */
    public Set<String> getServerVersions(ServerType serverType) {
        if (this.serverType == null) this.getServerTypes(); // first load hashmap
        Set<String> versions = this.serverType.get(serverType);
        if (versions == null) {
            versions = new HashSet<>();

            versions.add("1.14"); // TODO

            // TODO check if any version before adding
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

    public Plugin []getPlugins() {
        if (this.plugins == null) {
            this.plugins = new HashSet<>();
            // TODO
        }

        return this.plugins.toArray(new Plugin[0]);
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
