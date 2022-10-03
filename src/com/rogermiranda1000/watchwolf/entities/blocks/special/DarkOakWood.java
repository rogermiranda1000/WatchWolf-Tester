package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DarkOakWood(short id) {
		super(id, "DARK_OAK_WOOD");
	}

	public DarkOakWood(int id) {
		this((short) id);
	}

	private DarkOakWood(DarkOakWood old) {
		this(old.id);
	}

}