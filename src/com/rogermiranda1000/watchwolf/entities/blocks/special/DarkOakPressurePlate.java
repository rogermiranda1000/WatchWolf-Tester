package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DarkOakPressurePlate(short id) {
		super(id, "DARK_OAK_PRESSURE_PLATE");
	}

	public DarkOakPressurePlate(int id) {
		this((short) id);
	}

	private DarkOakPressurePlate(DarkOakPressurePlate old) {
		this(old.id);
	}

}