package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Farmland extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Farmland(short id) {
		super(id, "FARMLAND");
	}

	public Farmland(int id) {
		this((short) id);
	}

	private Farmland(Farmland old) {
		this(old.id);
	}

}