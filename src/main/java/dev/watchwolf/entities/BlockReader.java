package dev.watchwolf.entities;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.entities.blocks.Directionable;
import dev.watchwolf.entities.blocks.Orientable;

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
                    if ((tmp & 0b00_000001) > 0) orientable = orientable.setOrientation(Orientable.Orientation.U);
                    if ((tmp & 0b00_000010) > 0) orientable = orientable.setOrientation(Orientable.Orientation.D);
                    if ((tmp & 0b00_000100) > 0) orientable = orientable.setOrientation(Orientable.Orientation.N);
                    if ((tmp & 0b00_001000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.S);
                    if ((tmp & 0b00_010000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.E);
                    if ((tmp & 0b00_100000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.W);
                } catch (IllegalArgumentException ignore) {
                } finally {
                    r = (Block) orientable;
                }
            }
            if (r instanceof Directionable) {
                Directionable directionable = (Directionable) r;
                tmp >>= 6;
                try {
                    switch (tmp) {
                        case 1:
                            directionable = directionable.setDirection(Directionable.Direction.X);
                            break;
                        case 2:
                            directionable = directionable.setDirection(Directionable.Direction.Y);
                            break;
                        case 3:
                            directionable = directionable.setDirection(Directionable.Direction.Z);
                            break;
                    }
                } catch (IllegalArgumentException ignore) {}
                try {
                    switch (tmp) {
                        case 0:
                            directionable = directionable.setDirection(Directionable.Direction.NONE);
                            break;
                        case 1:
                            directionable = directionable.setDirection(Directionable.Direction.SINGLE_WALL);
                            break;
                        case 2:
                            directionable = directionable.setDirection(Directionable.Direction.DOUBLE_WALL);
                            break;
                    }
                } catch (IllegalArgumentException ignore) {}
                r = (Block) directionable;
            }

            SocketHelper.discard(dis, 1); // reserved
            SocketHelper.discard(dis, 51); // TODO
            return r;
        });
    }
}
