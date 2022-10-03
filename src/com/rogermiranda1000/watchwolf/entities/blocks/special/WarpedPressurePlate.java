package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WarpedPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedPressurePlate(short id) {
		super(id, "WARPED_PRESSURE_PLATE");
	}

	public WarpedPressurePlate(int id) {
		this((short) id);
	}

	private WarpedPressurePlate(WarpedPressurePlate old) {
		this(old.id);
	}

}