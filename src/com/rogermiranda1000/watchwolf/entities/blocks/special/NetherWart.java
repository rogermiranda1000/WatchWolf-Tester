package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class NetherWart extends Block {

	/*   --- CONSTRUCTORS ---   */
	public NetherWart(short id) {
		super(id, "NETHER_WART");
	}

	public NetherWart(int id) {
		this((short) id);
	}

	private NetherWart(NetherWart old) {
		this(old.id);
	}

}