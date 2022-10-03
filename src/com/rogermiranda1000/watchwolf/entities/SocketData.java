package com.rogermiranda1000.watchwolf.entities;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public abstract class SocketData {
    private static final HashMap<Class<? extends SocketData>, Function<DataInputStream,SocketData>> readers = new HashMap<>();

    public void setReaderFunction(Function<DataInputStream,SocketData> reader) {
        SocketData.readers.put(this.getClass(), reader);
    }

    public static void setReaderFunction(Class<? extends SocketData> targetClass, Function<DataInputStream,SocketData> reader) {
        SocketData.readers.put(targetClass, reader);
    }


    public static SocketData readSocketData(DataInputStream dis, Class<? extends SocketData> typeClass) throws UnknownReaderClassException {
        Function<DataInputStream,SocketData> reader = SocketData.readers.get(typeClass);
        if (reader == null) throw new UnknownReaderClassException("The class " + typeClass.getName() + " doesn't contain a SocketReader.");
        return reader.apply(dis);
    }

    public abstract void sendSocketData(ArrayList<Byte> out);
}
