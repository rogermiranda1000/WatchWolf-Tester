package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedDarkOakWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedDarkOakWood(short id) {
		super(id, "STRIPPED_DARK_OAK_WOOD");
	}

	public StrippedDarkOakWood(int id) {
		this((short) id);
	}

	private StrippedDarkOakWood(StrippedDarkOakWood old) {
		this(old.id);
	}

}