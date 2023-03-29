package dev.watchwolf.entities;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SocketData {
    public static interface Reader {
        public SocketData read(DataInputStream dis) throws IOException;
    }

    public SocketData() {}

    public SocketData(Reader reader) {
        this.setReaderFunction(reader);
    }

    private static final HashMap<Class<? extends SocketData>, Reader> readers = new HashMap<>();

    public void setReaderFunction(Reader reader) {
        SocketData.readers.put(this.getClass(), reader);
    }

    public static void setReaderFunction(Class<? extends SocketData> targetClass, Reader reader) {
        SocketData.readers.put(targetClass, reader);
    }

    public static void loadStaticBlock(Class<?> loaderClass) {
        try {
            Class.forName(loaderClass.getName());
        } catch (ClassNotFoundException ignore) { }
    }

    public static SocketData readSocketData(DataInputStream dis, Class<? extends SocketData> typeClass) throws UnknownReaderClassException, IOException {
        Reader reader = SocketData.readers.get(typeClass);
        if (reader == null) {
            // maybe it's not loaded yet
            SocketData.loadStaticBlock(typeClass);

            reader = SocketData.readers.get(typeClass);
            if (reader == null) throw new UnknownReaderClassException("The class " + typeClass.getName() + " doesn't contain a SocketReader.");
        }
        return reader.read(dis);
    }

    public abstract void sendSocketData(ArrayList<Byte> out);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SocketData)) return false;

        ArrayList<Byte> thisData = new ArrayList<>(),
                        thatData = new ArrayList<>();
        this.sendSocketData(thisData);
        ((SocketData)obj).sendSocketData(thatData);
        return thisData.equals(thatData);
    }
}
