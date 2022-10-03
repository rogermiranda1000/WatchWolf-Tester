package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedMangroveWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedMangroveWood(short id) {
		super(id, "STRIPPED_MANGROVE_WOOD");
	}

	public StrippedMangroveWood(int id) {
		this((short) id);
	}

	private StrippedMangroveWood(StrippedMangroveWood old) {
		this(old.id);
	}

}