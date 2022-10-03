package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BirchWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BirchWood(short id) {
		super(id, "BIRCH_WOOD");
	}

	public BirchWood(int id) {
		this((short) id);
	}

	private BirchWood(BirchWood old) {
		this(old.id);
	}

}