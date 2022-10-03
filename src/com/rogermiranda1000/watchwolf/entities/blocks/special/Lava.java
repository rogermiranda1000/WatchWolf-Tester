package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Lava extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Lava(int id) {
		super(id, "Lava");
	}

	private Lava(Lava old) {
		this(old.id);
	}

}