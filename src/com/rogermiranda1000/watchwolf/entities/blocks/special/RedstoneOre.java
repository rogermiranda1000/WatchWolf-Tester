package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedstoneOre extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedstoneOre(short id) {
		super(id, "REDSTONE_ORE");
	}

	public RedstoneOre(int id) {
		this((short) id);
	}

	private RedstoneOre(RedstoneOre old) {
		this(old.id);
	}

}