package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WarpedHyphae extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedHyphae(short id) {
		super(id, "WARPED_HYPHAE");
	}

	public WarpedHyphae(int id) {
		this((short) id);
	}

	private WarpedHyphae(WarpedHyphae old) {
		this(old.id);
	}

}