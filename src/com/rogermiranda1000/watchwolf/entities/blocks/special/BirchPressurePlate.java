package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BirchPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BirchPressurePlate(short id) {
		super(id, "BIRCH_PRESSURE_PLATE");
	}

	public BirchPressurePlate(int id) {
		this((short) id);
	}

	private BirchPressurePlate(BirchPressurePlate old) {
		this(old.id);
	}

}