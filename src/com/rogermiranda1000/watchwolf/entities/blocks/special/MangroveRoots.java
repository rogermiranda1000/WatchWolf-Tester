package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangroveRoots extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangroveRoots(short id) {
		super(id, "MANGROVE_ROOTS");
	}

	public MangroveRoots(int id) {
		this((short) id);
	}

	private MangroveRoots(MangroveRoots old) {
		this(old.id);
	}

}