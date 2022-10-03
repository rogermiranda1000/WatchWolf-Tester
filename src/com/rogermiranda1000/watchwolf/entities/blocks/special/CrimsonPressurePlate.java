package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CrimsonPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CrimsonPressurePlate(short id) {
		super(id, "CRIMSON_PRESSURE_PLATE");
	}

	public CrimsonPressurePlate(int id) {
		this((short) id);
	}

	private CrimsonPressurePlate(CrimsonPressurePlate old) {
		this(old.id);
	}

}