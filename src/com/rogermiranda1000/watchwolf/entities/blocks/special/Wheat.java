package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Wheat extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Wheat(int id) {
		super(id, "Wheat");
	}

	private Wheat(Wheat old) {
		this(old.id);
	}

}