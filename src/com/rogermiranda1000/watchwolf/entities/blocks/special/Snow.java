package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Snow extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Snow(short id) {
		super(id, "SNOW");
	}

	public Snow(int id) {
		this((short) id);
	}

	private Snow(Snow old) {
		this(old.id);
	}

}