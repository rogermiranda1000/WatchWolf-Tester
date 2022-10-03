package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class TurtleEgg extends Block {

	/*   --- CONSTRUCTORS ---   */
	public TurtleEgg(short id) {
		super(id, "TURTLE_EGG");
	}

	public TurtleEgg(int id) {
		this((short) id);
	}

	private TurtleEgg(TurtleEgg old) {
		this(old.id);
	}

}