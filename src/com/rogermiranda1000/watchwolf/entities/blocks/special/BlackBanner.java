package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlackBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BlackBanner(short id) {
		super(id, "BLACK_BANNER");
	}

	public BlackBanner(int id) {
		this((short) id);
	}

	private BlackBanner(BlackBanner old) {
		this(old.id);
	}

}