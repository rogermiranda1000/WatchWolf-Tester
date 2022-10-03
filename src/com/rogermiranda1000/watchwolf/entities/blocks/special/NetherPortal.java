package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class NetherPortal extends Block {

	/*   --- CONSTRUCTORS ---   */
	public NetherPortal(short id) {
		super(id, "NETHER_PORTAL");
	}

	public NetherPortal(int id) {
		this((short) id);
	}

	private NetherPortal(NetherPortal old) {
		this(old.id);
	}

}