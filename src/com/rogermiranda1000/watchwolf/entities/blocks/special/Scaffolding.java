package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Scaffolding extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Scaffolding(short id) {
		super(id, "SCAFFOLDING");
	}

	public Scaffolding(int id) {
		this((short) id);
	}

	private Scaffolding(Scaffolding old) {
		this(old.id);
	}

}