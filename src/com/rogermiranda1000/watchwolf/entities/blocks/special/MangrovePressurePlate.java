package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangrovePressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangrovePressurePlate(short id) {
		super(id, "MANGROVE_PRESSURE_PLATE");
	}

	public MangrovePressurePlate(int id) {
		this((short) id);
	}

	private MangrovePressurePlate(MangrovePressurePlate old) {
		this(old.id);
	}

}