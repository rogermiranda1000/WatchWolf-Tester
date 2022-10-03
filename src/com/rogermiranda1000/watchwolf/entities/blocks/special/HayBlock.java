package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class HayBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public HayBlock(short id) {
		super(id, "HAY_BLOCK");
	}

	public HayBlock(int id) {
		this((short) id);
	}

	private HayBlock(HayBlock old) {
		this(old.id);
	}

}