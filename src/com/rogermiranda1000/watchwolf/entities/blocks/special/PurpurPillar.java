package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PurpurPillar extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PurpurPillar(short id) {
		super(id, "PURPUR_PILLAR");
	}

	public PurpurPillar(int id) {
		this((short) id);
	}

	private PurpurPillar(PurpurPillar old) {
		this(old.id);
	}

}