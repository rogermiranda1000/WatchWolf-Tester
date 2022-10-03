package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OakWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OakWood(short id) {
		super(id, "OAK_WOOD");
	}

	public OakWood(int id) {
		this((short) id);
	}

	private OakWood(OakWood old) {
		this(old.id);
	}

}