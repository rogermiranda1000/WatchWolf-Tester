package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedOakWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedOakWood(short id) {
		super(id, "STRIPPED_OAK_WOOD");
	}

	public StrippedOakWood(int id) {
		this((short) id);
	}

	private StrippedOakWood(StrippedOakWood old) {
		this(old.id);
	}

}