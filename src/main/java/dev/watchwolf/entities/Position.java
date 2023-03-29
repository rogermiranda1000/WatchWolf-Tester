package dev.watchwolf.entities;

import java.util.ArrayList;

public class Position extends SocketData {
    static {
        setReaderFunction(Position.class, (dis) -> {
            String world = SocketHelper.readString(dis);
            double x = SocketHelper.readDouble(dis);
            double y = SocketHelper.readDouble(dis);
            double z = SocketHelper.readDouble(dis);

            return new Position(world, x, y, z);
        });
    }

    final private String world;
    final private double x, y, z;

    public Position(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorld() {
        return this.world;
    }

    public double getX() {
        return this.x;
    }

    public int getBlockX() {
        return (int)this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getBlockY() {
        return (int)this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getBlockZ() {
        return (int)this.z;
    }

    public Position add(Position pos) throws IllegalArgumentException {
        if (!this.world.equals(pos.world)) throw new IllegalArgumentException("You can only add positions in the same world");
        return this.add(pos.x, pos.y, pos.z);
    }

    public Position add(double x, double y, double z) {
        return new Position(this.world, this.x + x, this.y + y, this.z + z);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addString(out, this.world);
        SocketHelper.addDouble(out, this.x);
        SocketHelper.addDouble(out, this.y);
        SocketHelper.addDouble(out, this.z);
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.z + " (" + this.world + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj, 0.0f);
    }

    public boolean equals(Object obj, float margin) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;

        Position that = (Position)obj;
        return this.world.equals(that.world) && Math.abs(this.x - that.x) <= margin && Math.abs(this.y - that.y) <= margin && Math.abs(this.z - that.z) <= margin;
    }
}
