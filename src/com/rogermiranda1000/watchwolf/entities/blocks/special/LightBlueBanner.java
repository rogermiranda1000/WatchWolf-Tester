package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightBlueBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightBlueBanner(short id) {
		super(id, "LIGHT_BLUE_BANNER");
	}

	public LightBlueBanner(int id) {
		this((short) id);
	}

	private LightBlueBanner(LightBlueBanner old) {
		this(old.id);
	}

}