package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Water extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Water(int id) {
		super(id, "Water");
	}

	private Water(Water old) {
		this(old.id);
	}

}