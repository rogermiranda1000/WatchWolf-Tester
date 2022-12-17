package com.rogermiranda1000.watchwolf.entities.entities;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.SocketData;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import com.rogermiranda1000.watchwolf.entities.items.Item;

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

    static {
        SocketData.setReaderFunction(Entity.class, (dis) -> {
            EntityType type = EntityType.values()[SocketHelper.readShort(dis)];
            Position pos = (Position) SocketData.readSocketData(dis, Position.class);
            String uuid = SocketHelper.readString(dis);

            if (type.equals(EntityType.DroppedItem)) {
                Item droppedItem = (Item) SocketData.readSocketData(dis, Item.class);
                return new DroppedItem(uuid, pos, droppedItem);
            }
            return null;
        });
    }
}
