package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class WarpedPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedPressurePlate(int id) {
		super(id, "WarpedPressurePlate");
	}

	private WarpedPressurePlate(WarpedPressurePlate old) {
		this(old.id);
	}

}