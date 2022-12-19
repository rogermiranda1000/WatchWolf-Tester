package dev.watchwolf.entities.blocks;

import dev.watchwolf.entities.SocketData;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.items.ItemType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Block extends SocketData {
    public interface BlockFieldGetter {
        String getBlockField(Block instance, Field field);
    }

    protected final short id;
    protected final String name;

    /**
     * For the toString method.
     * It needs to be object-dependant because of IllegalAccessException protection.
     */
    private final BlockFieldGetter fieldToStringGetter;

    protected Block(short id, String name, BlockFieldGetter fieldToStringGetter) {
        this.id = id;
        this.name = name;
        this.fieldToStringGetter = fieldToStringGetter;
    }

    protected Block(short id, String name) {
        this(id, name, (ins, f) -> {
            try {
                return f.get(ins).toString();
            } catch (IllegalAccessException ignore) {
                return "?";
            }
        });
    }

    protected Block(int id, String name) {
        this((short) id, name);
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ItemType getItemType() {
        return ItemType.valueOf(this.getName().toUpperCase());
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, this.id);
        SocketHelper.fill(out, 54); // fill 54 bytes of unused arguments
    }

    @Override
    public String toString() {
        // extra variables?
        String variables = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RelevantBlockData.class))
                .map(f -> f.getName()+"="+this.fieldToStringGetter.getBlockField(this, f))
                .collect(Collectors.joining(", "));

        return this.name + (variables.length() > 0 ? ("{" + variables + "}") : "");
    }
}
