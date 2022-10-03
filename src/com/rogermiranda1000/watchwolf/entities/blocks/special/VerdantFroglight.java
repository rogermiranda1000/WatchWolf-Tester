package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class VerdantFroglight extends Block {

	/*   --- CONSTRUCTORS ---   */
	public VerdantFroglight(short id) {
		super(id, "VERDANT_FROGLIGHT");
	}

	public VerdantFroglight(int id) {
		this((short) id);
	}

	private VerdantFroglight(VerdantFroglight old) {
		this(old.id);
	}

}