package com.rogermiranda1000.watchwolf.entities;

import java.util.ArrayList;

public class Position extends SocketData {
    static {
        SocketData.setReaderFunction(Position.class, (dis) -> {
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

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addString(out, this.world);
        SocketHelper.addDouble(out, this.x);
        SocketHelper.addDouble(out, this.y);
        SocketHelper.addDouble(out, this.z);
    }
}