package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AcaciaLeaves(short id) {
		super(id, "ACACIA_LEAVES");
	}

	public AcaciaLeaves(int id) {
		this((short) id);
	}

	private AcaciaLeaves(AcaciaLeaves old) {
		this(old.id);
	}

}