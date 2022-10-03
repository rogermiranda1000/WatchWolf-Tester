package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WhiteBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WhiteBanner(short id) {
		super(id, "WHITE_BANNER");
	}

	public WhiteBanner(int id) {
		this((short) id);
	}

	private WhiteBanner(WhiteBanner old) {
		this(old.id);
	}

}