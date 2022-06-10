package com.rogermiranda1000.entities;

public class UsualPlugin extends Plugin {
    private final String name;
    private final String version;
    private final boolean isPremium;

    public UsualPlugin(String name, String version, boolean isPremium) {
        this.name = name;
        this.version = version;
        this.isPremium = isPremium;
    }
}
