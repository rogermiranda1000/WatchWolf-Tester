package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PurpleBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PurpleBanner(short id) {
		super(id, "PURPLE_BANNER");
	}

	public PurpleBanner(int id) {
		this((short) id);
	}

	private PurpleBanner(PurpleBanner old) {
		this(old.id);
	}

}