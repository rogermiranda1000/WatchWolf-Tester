package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OrangeBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OrangeBanner(short id) {
		super(id, "ORANGE_BANNER");
	}

	public OrangeBanner(int id) {
		this((short) id);
	}

	private OrangeBanner(OrangeBanner old) {
		this(old.id);
	}

}