package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BirchLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BirchLeaves(short id) {
		super(id, "BIRCH_LEAVES");
	}

	public BirchLeaves(int id) {
		this((short) id);
	}

	private BirchLeaves(BirchLeaves old) {
		this(old.id);
	}

}