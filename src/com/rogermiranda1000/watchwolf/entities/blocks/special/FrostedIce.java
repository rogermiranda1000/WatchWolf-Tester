package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class FrostedIce extends Block {

	/*   --- CONSTRUCTORS ---   */
	public FrostedIce(short id) {
		super(id, "FROSTED_ICE");
	}

	public FrostedIce(int id) {
		this((short) id);
	}

	private FrostedIce(FrostedIce old) {
		this(old.id);
	}

}