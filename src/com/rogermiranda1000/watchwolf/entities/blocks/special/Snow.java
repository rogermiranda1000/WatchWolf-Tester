package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Snow extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Snow(int id) {
		super(id, "Snow");
	}

	private Snow(Snow old) {
		this(old.id);
	}

}