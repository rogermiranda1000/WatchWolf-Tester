package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OakPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OakPressurePlate(short id) {
		super(id, "OAK_PRESSURE_PLATE");
	}

	public OakPressurePlate(int id) {
		this((short) id);
	}

	private OakPressurePlate(OakPressurePlate old) {
		this(old.id);
	}

}