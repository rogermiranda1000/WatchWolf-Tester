package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SprucePressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SprucePressurePlate(short id) {
		super(id, "SPRUCE_PRESSURE_PLATE");
	}

	public SprucePressurePlate(int id) {
		this((short) id);
	}

	private SprucePressurePlate(SprucePressurePlate old) {
		this(old.id);
	}

}