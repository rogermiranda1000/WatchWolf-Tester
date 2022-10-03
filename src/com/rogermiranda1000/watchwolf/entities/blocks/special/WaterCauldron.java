package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class WaterCauldron extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WaterCauldron(int id) {
		super(id, "WaterCauldron");
	}

	private WaterCauldron(WaterCauldron old) {
		this(old.id);
	}

}