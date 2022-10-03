package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PowderSnowCauldron extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PowderSnowCauldron(short id) {
		super(id, "POWDER_SNOW_CAULDRON");
	}

	public PowderSnowCauldron(int id) {
		this((short) id);
	}

	private PowderSnowCauldron(PowderSnowCauldron old) {
		this(old.id);
	}

}