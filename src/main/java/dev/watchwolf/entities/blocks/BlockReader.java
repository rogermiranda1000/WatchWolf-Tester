package dev.watchwolf.entities.blocks;

import dev.watchwolf.entities.SocketData;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.transformer.*;

public class BlockReader {
    /**
     * Bytes that the Block packet has. The first 2 specifies the block itself,
     * and the rest adds information to the block. Refer to the
     * <a href="https://github.com/watch-wolf/WatchWolf/blob/9d3a6016b5823aba1ee61187349e13c0edfe9c5f/Standard/Protocols.pdf">API documentation</a>,
     * under subsection 2.4.9. Block.
     */
    public static final int BLOCK_SOCKET_DATA_SIZE = 56;

    static {
        SocketData.setReaderFunction(Block.class, (dis) -> {
            Block r = Blocks.getBlockById(SocketHelper.readShort(dis));
            int []blockData = new int[BlockReader.BLOCK_SOCKET_DATA_SIZE];
            for (int i = 2; i < blockData.length; i++) blockData[i] = dis.readUnsignedByte(); // read 54 bytes (the first 2 were already readed)
            if (r == null) return null;

            r = Transformers.getInstance().loadAllSocketData(r, blockData);

            return r;
        });
    }
}
