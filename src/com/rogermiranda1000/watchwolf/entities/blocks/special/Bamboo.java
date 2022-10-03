package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Bamboo extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Bamboo(short id) {
		super(id, "BAMBOO");
	}

	public Bamboo(int id) {
		this((short) id);
	}

	private Bamboo(Bamboo old) {
		this(old.id);
	}

}