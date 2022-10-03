package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Cake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Cake(int id) {
		super(id, "Cake");
	}

	private Cake(Cake old) {
		this(old.id);
	}

}