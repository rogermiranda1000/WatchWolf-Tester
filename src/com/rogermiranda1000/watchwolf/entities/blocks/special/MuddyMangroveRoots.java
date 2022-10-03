package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MuddyMangroveRoots extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MuddyMangroveRoots(short id) {
		super(id, "MUDDY_MANGROVE_ROOTS");
	}

	public MuddyMangroveRoots(int id) {
		this((short) id);
	}

	private MuddyMangroveRoots(MuddyMangroveRoots old) {
		this(old.id);
	}

}