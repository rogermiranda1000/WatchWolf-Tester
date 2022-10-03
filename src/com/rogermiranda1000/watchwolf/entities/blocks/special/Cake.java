package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Cake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Cake(short id) {
		super(id, "CAKE");
	}

	public Cake(int id) {
		this((short) id);
	}

	private Cake(Cake old) {
		this(old.id);
	}

}