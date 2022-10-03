package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedSpruceWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedSpruceWood(short id) {
		super(id, "STRIPPED_SPRUCE_WOOD");
	}

	public StrippedSpruceWood(int id) {
		this((short) id);
	}

	private StrippedSpruceWood(StrippedSpruceWood old) {
		this(old.id);
	}

}