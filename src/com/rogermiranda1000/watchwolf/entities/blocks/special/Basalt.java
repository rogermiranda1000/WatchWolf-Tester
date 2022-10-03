package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Basalt extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Basalt(short id) {
		super(id, "BASALT");
	}

	public Basalt(int id) {
		this((short) id);
	}

	private Basalt(Basalt old) {
		this(old.id);
	}

}