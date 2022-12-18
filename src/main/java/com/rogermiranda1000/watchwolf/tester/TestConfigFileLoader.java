package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.entities.files.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.files.Plugin;
import com.rogermiranda1000.watchwolf.entities.files.WorldFile;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * All AbstractTest's instances will have one of those objects to get the necessary data in order to run the tests.
 * The problem is that only `beforeAll` needs the information, and JUnit creates multiple instances to fulfill the tests,
 * that's why we won't load any part of the file until it is explicitly requested.
 */
public class TestConfigFileLoader {
    private final String file;
    private HashMap<String,Object> loadedYaml;

    /**
     * Server type, with all its versions
     */
    private HashMap<ServerType,Set<String>> serverType;
    private Boolean overrideSync;
    private Plugin plugin;
    private Set<Plugin> extraPlugins;
    private Set<WorldFile> maps;
    private Set<ConfigFile> configFiles;
    private Set<String> users;

    public TestConfigFileLoader(String file) throws IOException {
        this.file = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
    }

    private <T> T getEntry(Function<HashMap<String,Object>,T> getter) {
        Yaml yaml = new Yaml();
        if (this.loadedYaml == null) this.loadedYaml = yaml.load(this.file);
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
    public Set<String> getServerVersions(final ServerType serverType) throws ConfigFileException {
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

    public Plugin getPlugin() throws ConfigFileException {
        if (this.plugin == null) {
            String r = this.getEntry(it -> (String) it.get("plugin"));
            if (r == null) throw new ConfigFileException("The config file must contain a plugin to test.");
            try {
                this.plugin = PluginBuilder.build(r);
            } catch (IOException ex) { throw new ConfigFileException(ex); }
        }

        return this.plugin;
    }

    public Plugin []getExtraPlugins() throws ConfigFileException {
        if (this.extraPlugins == null) {
            this.extraPlugins = new HashSet<>();
            ArrayList<String> r = this.getEntry(it -> (ArrayList<String>) it.get("extra-plugins"));
            if (r != null) { // maybe any extra plugin?
                for (String path : r) {
                    try {
                        this.extraPlugins.add(PluginBuilder.build(path));
                    } catch (IOException ex) { throw new ConfigFileException("Couldn't load " + path, ex); }
                }
            }
        }

        return this.extraPlugins.toArray(new Plugin[0]);
    }

    public String []getUsers() {
        if (this.users == null) {
            this.users = new HashSet<>();
            ArrayList<String> r = this.getEntry(it -> (ArrayList<String>) it.get("users"));
            if (r != null) { // maybe any user?
                this.users.addAll(r);
            }
        }

        return this.users.toArray(new String[0]);
    }

    public WorldFile []getMaps() throws ConfigFileException {
        if (this.maps == null) {
            this.maps = new HashSet<>();
            final AtomicReference<String> crash = new AtomicReference<>();
            ArrayList<WorldFile> r = this.getEntry(it -> {
                ArrayList<WorldFile> worlds = new ArrayList<>();
                for (LinkedHashMap<String,String> w : (ArrayList<LinkedHashMap<String,String>>)it.get("maps")) {
                    for (Map.Entry<String,String> map : w.entrySet()) {
                        try {
                            worlds.add(new WorldFile(map.getKey(), map.getValue()));
                        } catch (IOException ex) {
                            crash.set(map.getValue());
                            return worlds;
                        }
                    }
                }
                return worlds;
            });

            if (crash.get() != null) throw new ConfigFileException("Error while loading world file '" + crash.get() + "'");
            if (r != null) this.maps.addAll(r);
        }

        return this.maps.toArray(new WorldFile[0]);
    }

    public ConfigFile []getConfigFiles() throws ConfigFileException {
        if (this.configFiles == null) {
            this.configFiles = new HashSet<>();
            final AtomicReference<String> crash = new AtomicReference<>();
            ArrayList<ConfigFile> r = this.getEntry(it -> {
                ArrayList<ConfigFile> files = new ArrayList<>();
                for (Object f : (ArrayList<Object>)it.get("config-files")) {
                    if (f instanceof String) {
                        try {
                            files.add(new ConfigFile((String) f, "plugins/"));
                        } catch (IOException ex) {
                            crash.set((String) f);
                            return files;
                        }
                    }
                    else if (f instanceof LinkedHashMap) {
                        for (Map.Entry<String,String> file : ((LinkedHashMap<String,String>)f).entrySet()) {
                            try {
                                files.add(new ConfigFile(file.getValue(), "plugins/" + file.getKey()));
                            } catch (IOException ex) {
                                crash.set(file.getValue());
                                return files;
                            }
                        }
                    }
                    else {
                        crash.set("typeof " + f.getClass().toString());
                        return files;
                    }
                }
                return files;
            });

            if (crash.get() != null) throw new ConfigFileException("Error while loading config file '" + crash.get() + "'");
            if (r != null) this.configFiles.addAll(r);
        }

        return this.configFiles.toArray(new ConfigFile[0]);
    }
}
