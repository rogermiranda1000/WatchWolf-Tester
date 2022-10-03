package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightGrayBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightGrayBanner(short id) {
		super(id, "LIGHT_GRAY_BANNER");
	}

	public LightGrayBanner(int id) {
		this((short) id);
	}

	private LightGrayBanner(LightGrayBanner old) {
		this(old.id);
	}

}