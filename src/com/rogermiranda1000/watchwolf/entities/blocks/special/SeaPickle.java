package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SeaPickle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SeaPickle(short id) {
		super(id, "SEA_PICKLE");
	}

	public SeaPickle(int id) {
		this((short) id);
	}

	private SeaPickle(SeaPickle old) {
		this(old.id);
	}

}