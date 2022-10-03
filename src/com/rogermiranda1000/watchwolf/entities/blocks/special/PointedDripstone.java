package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class PointedDripstone extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PointedDripstone(int id) {
		super(id, "PointedDripstone");
	}

	private PointedDripstone(PointedDripstone old) {
		this(old.id);
	}

}