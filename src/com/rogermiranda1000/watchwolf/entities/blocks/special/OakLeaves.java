package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OakLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OakLeaves(short id) {
		super(id, "OAK_LEAVES");
	}

	public OakLeaves(int id) {
		this((short) id);
	}

	private OakLeaves(OakLeaves old) {
		this(old.id);
	}

}