package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class HangingRoots extends Block {

	/*   --- CONSTRUCTORS ---   */
	public HangingRoots(short id) {
		super(id, "HANGING_ROOTS");
	}

	public HangingRoots(int id) {
		this((short) id);
	}

	private HangingRoots(HangingRoots old) {
		this(old.id);
	}

}