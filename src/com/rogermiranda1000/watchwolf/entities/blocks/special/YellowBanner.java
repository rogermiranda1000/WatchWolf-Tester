package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class YellowBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public YellowBanner(short id) {
		super(id, "YELLOW_BANNER");
	}

	public YellowBanner(int id) {
		this((short) id);
	}

	private YellowBanner(YellowBanner old) {
		this(old.id);
	}

}