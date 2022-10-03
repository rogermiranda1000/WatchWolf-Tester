package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedBirchWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedBirchWood(short id) {
		super(id, "STRIPPED_BIRCH_WOOD");
	}

	public StrippedBirchWood(int id) {
		this((short) id);
	}

	private StrippedBirchWood(StrippedBirchWood old) {
		this(old.id);
	}

}