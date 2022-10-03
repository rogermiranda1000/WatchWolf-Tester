package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LimeBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LimeBanner(short id) {
		super(id, "LIME_BANNER");
	}

	public LimeBanner(int id) {
		this((short) id);
	}

	private LimeBanner(LimeBanner old) {
		this(old.id);
	}

}