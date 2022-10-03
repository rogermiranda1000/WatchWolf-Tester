package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangroveLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangroveLeaves(short id) {
		super(id, "MANGROVE_LEAVES");
	}

	public MangroveLeaves(int id) {
		this((short) id);
	}

	private MangroveLeaves(MangroveLeaves old) {
		this(old.id);
	}

}