package dev.watchwolf.entities.blocks;

import dev.watchwolf.entities.SocketData;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.transformer.*;

public class BlockReader {
    static {
        SocketData.setReaderFunction(Block.class, (dis) -> {
            Block r = Blocks.getBlockById(SocketHelper.readShort(dis));
            int []blockData = new int[AbstractTransformer.BLOCK_SOCKET_DATA_SIZE];
            for (int i = 2; i < blockData.length; i++) blockData[i] = dis.readUnsignedByte(); // read 54 bytes (the first 2 were already readed)
            if (r == null) return null;

            for (AbstractTransformer<?,?> transformer : Transformers.getTransformers()) {
                r = transformer.loadSocketData(r, blockData);
            }

            return r;
        });
    }
}
