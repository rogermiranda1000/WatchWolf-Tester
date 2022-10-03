package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PinkBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PinkBanner(short id) {
		super(id, "PINK_BANNER");
	}

	public PinkBanner(int id) {
		this((short) id);
	}

	private PinkBanner(PinkBanner old) {
		this(old.id);
	}

}