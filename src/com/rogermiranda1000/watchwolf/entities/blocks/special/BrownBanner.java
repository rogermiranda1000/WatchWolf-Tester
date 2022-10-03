package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BrownBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BrownBanner(short id) {
		super(id, "BROWN_BANNER");
	}

	public BrownBanner(int id) {
		this((short) id);
	}

	private BrownBanner(BrownBanner old) {
		this(old.id);
	}

}