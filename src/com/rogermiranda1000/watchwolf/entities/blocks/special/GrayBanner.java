package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GrayBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GrayBanner(short id) {
		super(id, "GRAY_BANNER");
	}

	public GrayBanner(int id) {
		this((short) id);
	}

	private GrayBanner(GrayBanner old) {
		this(old.id);
	}

}