package com.rogermiranda1000.watchwolf.entities;

import com.rogermiranda1000.watchwolf.entities.blocks.Block;

public class BlockReader {
    static {
        SocketData.setReaderFunction(Block.class, (dis) -> {
            // TODO
            return null;
        });
    }
}
