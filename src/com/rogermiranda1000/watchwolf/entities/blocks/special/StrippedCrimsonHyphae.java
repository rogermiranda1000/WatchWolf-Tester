package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedCrimsonHyphae extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedCrimsonHyphae(short id) {
		super(id, "STRIPPED_CRIMSON_HYPHAE");
	}

	public StrippedCrimsonHyphae(int id) {
		this((short) id);
	}

	private StrippedCrimsonHyphae(StrippedCrimsonHyphae old) {
		this(old.id);
	}

}