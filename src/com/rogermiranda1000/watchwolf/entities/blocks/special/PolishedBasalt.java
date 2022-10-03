package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PolishedBasalt extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PolishedBasalt(short id) {
		super(id, "POLISHED_BASALT");
	}

	public PolishedBasalt(int id) {
		this((short) id);
	}

	private PolishedBasalt(PolishedBasalt old) {
		this(old.id);
	}

}