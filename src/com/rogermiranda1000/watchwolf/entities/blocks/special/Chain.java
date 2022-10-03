package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Chain extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Chain(short id) {
		super(id, "CHAIN");
	}

	public Chain(int id) {
		this((short) id);
	}

	private Chain(Chain old) {
		this(old.id);
	}

}