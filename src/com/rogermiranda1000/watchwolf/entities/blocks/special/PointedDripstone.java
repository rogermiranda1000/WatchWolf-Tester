package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PointedDripstone extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PointedDripstone(short id) {
		super(id, "POINTED_DRIPSTONE");
	}

	public PointedDripstone(int id) {
		this((short) id);
	}

	private PointedDripstone(PointedDripstone old) {
		this(old.id);
	}

}