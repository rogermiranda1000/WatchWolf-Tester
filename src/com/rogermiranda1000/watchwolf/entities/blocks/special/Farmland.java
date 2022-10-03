package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Farmland extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Farmland(int id) {
		super(id, "Farmland");
	}

	private Farmland(Farmland old) {
		this(old.id);
	}

}