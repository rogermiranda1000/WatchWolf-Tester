package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StonePressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StonePressurePlate(short id) {
		super(id, "STONE_PRESSURE_PLATE");
	}

	public StonePressurePlate(int id) {
		this((short) id);
	}

	private StonePressurePlate(StonePressurePlate old) {
		this(old.id);
	}

}