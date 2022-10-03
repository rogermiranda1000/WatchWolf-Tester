package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PolishedBlackstonePressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PolishedBlackstonePressurePlate(short id) {
		super(id, "POLISHED_BLACKSTONE_PRESSURE_PLATE");
	}

	public PolishedBlackstonePressurePlate(int id) {
		this((short) id);
	}

	private PolishedBlackstonePressurePlate(PolishedBlackstonePressurePlate old) {
		this(old.id);
	}

}