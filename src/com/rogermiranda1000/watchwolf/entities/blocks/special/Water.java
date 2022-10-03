package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Water extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Water(short id) {
		super(id, "WATER");
	}

	public Water(int id) {
		this((short) id);
	}

	private Water(Water old) {
		this(old.id);
	}

}