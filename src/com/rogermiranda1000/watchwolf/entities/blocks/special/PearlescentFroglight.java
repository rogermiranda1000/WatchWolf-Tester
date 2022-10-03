package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PearlescentFroglight extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PearlescentFroglight(short id) {
		super(id, "PEARLESCENT_FROGLIGHT");
	}

	public PearlescentFroglight(int id) {
		this((short) id);
	}

	private PearlescentFroglight(PearlescentFroglight old) {
		this(old.id);
	}

}