package dev.watchwolf.entities;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.entities.blocks.Directionable;
import dev.watchwolf.entities.blocks.Orientable;
import dev.watchwolf.entities.blocks.transformer.*;

public class BlockReader {
    static {
        SocketData.setReaderFunction(Block.class, (dis) -> {
            Block r = Blocks.getBlockById(SocketHelper.readShort(dis));
            int []blockData = new int[AbstractTransformer.BLOCK_SOCKET_DATA_SIZE];
            for (int i = 2; i < blockData.length; i++) blockData[i] = dis.readUnsignedByte(); // read 54 bytes (the first 2 were already readed)
            if (r == null) return null;

            r = AgeableTransformer.getInstance().loadSocketData(r, blockData);
            r = OrientableTransformer.getInstance().loadSocketData(r, blockData);
            r = DirectionableTransformer.getInstance().loadSocketData(r, blockData);
            r = GroupableTransformer.getInstance().loadSocketData(r, blockData);
            r = DelayableTransformer.getInstance().loadSocketData(r, blockData);

            return r;
        });
    }
}
