package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedWarpedHyphae extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedWarpedHyphae(short id) {
		super(id, "STRIPPED_WARPED_HYPHAE");
	}

	public StrippedWarpedHyphae(int id) {
		this((short) id);
	}

	private StrippedWarpedHyphae(StrippedWarpedHyphae old) {
		this(old.id);
	}

}