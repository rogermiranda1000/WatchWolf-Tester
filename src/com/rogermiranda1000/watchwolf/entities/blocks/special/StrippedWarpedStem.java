package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedWarpedStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedWarpedStem(short id) {
		super(id, "STRIPPED_WARPED_STEM");
	}

	public StrippedWarpedStem(int id) {
		this((short) id);
	}

	private StrippedWarpedStem(StrippedWarpedStem old) {
		this(old.id);
	}

}