package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WaterCauldron extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WaterCauldron(short id) {
		super(id, "WATER_CAULDRON");
	}

	public WaterCauldron(int id) {
		this((short) id);
	}

	private WaterCauldron(WaterCauldron old) {
		this(old.id);
	}

}