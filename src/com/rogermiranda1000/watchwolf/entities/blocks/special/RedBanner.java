package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedBanner(short id) {
		super(id, "RED_BANNER");
	}

	public RedBanner(int id) {
		this((short) id);
	}

	private RedBanner(RedBanner old) {
		this(old.id);
	}

}