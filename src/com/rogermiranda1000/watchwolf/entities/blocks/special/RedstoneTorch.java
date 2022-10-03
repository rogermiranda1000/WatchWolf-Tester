package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedstoneTorch extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedstoneTorch(short id) {
		super(id, "REDSTONE_TORCH");
	}

	public RedstoneTorch(int id) {
		this((short) id);
	}

	private RedstoneTorch(RedstoneTorch old) {
		this(old.id);
	}

}