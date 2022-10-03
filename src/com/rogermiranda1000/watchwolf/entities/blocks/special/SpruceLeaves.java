package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SpruceLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SpruceLeaves(short id) {
		super(id, "SPRUCE_LEAVES");
	}

	public SpruceLeaves(int id) {
		this((short) id);
	}

	private SpruceLeaves(SpruceLeaves old) {
		this(old.id);
	}

}