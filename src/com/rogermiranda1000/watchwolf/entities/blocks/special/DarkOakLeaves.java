package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DarkOakLeaves(short id) {
		super(id, "DARK_OAK_LEAVES");
	}

	public DarkOakLeaves(int id) {
		this((short) id);
	}

	private DarkOakLeaves(DarkOakLeaves old) {
		this(old.id);
	}

}