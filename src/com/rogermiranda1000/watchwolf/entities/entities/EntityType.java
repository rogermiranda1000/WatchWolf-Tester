package com.rogermiranda1000.watchwolf.entities.entities;

public enum EntityType {
    DroppedItem;

    /**
     * Get the EntityType using a class
     * @param cls Entity subclass
     * @return EntityType of the class
     * @throws IllegalArgumentException shouldn't happen; the specified class doesn't exist as EntityType
     */
    public static EntityType getType(Class<? extends Entity> cls) throws IllegalArgumentException {
        return EntityType.valueOf(cls.getSimpleName());
    }

    /**
     * Get the EntityType using an instance
     * @param e Entity instance
     * @return EntityType of the instance
     * @throws IllegalArgumentException shouldn't happen; the specified class doesn't exist as EntityType
     */
    public static EntityType getType(Entity e) throws IllegalArgumentException {
        return EntityType.getType(e.getClass());
    }
}
