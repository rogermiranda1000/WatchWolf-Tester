package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangroveWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangroveWood(short id) {
		super(id, "MANGROVE_WOOD");
	}

	public MangroveWood(int id) {
		this((short) id);
	}

	private MangroveWood(MangroveWood old) {
		this(old.id);
	}

}