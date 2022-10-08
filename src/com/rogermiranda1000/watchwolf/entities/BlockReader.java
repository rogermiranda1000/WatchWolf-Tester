package com.rogermiranda1000.watchwolf.entities;

import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.blocks.Blocks;
import com.rogermiranda1000.watchwolf.entities.blocks.Orientable;

public class BlockReader {
    static {
        SocketData.setReaderFunction(Block.class, (dis) -> {
            Block r = Blocks.getBlockById(SocketHelper.readShort(dis));
            if (r == null) {
                SocketHelper.discard(dis, 54);
                return null;
            }

            dis.readUnsignedByte(); // TODO age
            int tmp = dis.readUnsignedByte(); // direction & axis
            if (r instanceof Orientable) {
                Orientable orientable = (Orientable) r;
                try {
                    if ((tmp & 0b00_000001) > 0) orientable = orientable.set(Orientable.Orientation.U);
                    if ((tmp & 0b00_000010) > 0) orientable = orientable.set(Orientable.Orientation.D);
                    if ((tmp & 0b00_000100) > 0) orientable = orientable.set(Orientable.Orientation.N);
                    if ((tmp & 0b00_001000) > 0) orientable = orientable.set(Orientable.Orientation.S);
                    if ((tmp & 0b00_010000) > 0) orientable = orientable.set(Orientable.Orientation.E);
                    if ((tmp & 0b00_100000) > 0) orientable = orientable.set(Orientable.Orientation.W);
                } catch (IllegalArgumentException ignore) {
                } finally {
                    r = (Block) orientable;
                }
            }
            // TODO axis
            SocketHelper.discard(dis, 1); // reserved
            SocketHelper.discard(dis, 51); // TODO
            return r;
        });
    }
}
