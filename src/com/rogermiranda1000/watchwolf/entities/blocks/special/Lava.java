package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Lava extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Lava(short id) {
		super(id, "LAVA");
	}

	public Lava(int id) {
		this((short) id);
	}

	private Lava(Lava old) {
		this(old.id);
	}

}