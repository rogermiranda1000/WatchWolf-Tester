package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WarpedStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedStem(short id) {
		super(id, "WARPED_STEM");
	}

	public WarpedStem(int id) {
		this((short) id);
	}

	private WarpedStem(WarpedStem old) {
		this(old.id);
	}

}