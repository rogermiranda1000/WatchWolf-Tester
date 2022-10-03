package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SpruceWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SpruceWood(short id) {
		super(id, "SPRUCE_WOOD");
	}

	public SpruceWood(int id) {
		this((short) id);
	}

	private SpruceWood(SpruceWood old) {
		this(old.id);
	}

}