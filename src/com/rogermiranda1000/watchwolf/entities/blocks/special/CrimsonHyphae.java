package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CrimsonHyphae extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CrimsonHyphae(short id) {
		super(id, "CRIMSON_HYPHAE");
	}

	public CrimsonHyphae(int id) {
		this((short) id);
	}

	private CrimsonHyphae(CrimsonHyphae old) {
		this(old.id);
	}

}