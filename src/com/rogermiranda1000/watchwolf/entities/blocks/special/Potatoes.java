package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Potatoes extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Potatoes(int id) {
		super(id, "Potatoes");
	}

	private Potatoes(Potatoes old) {
		this(old.id);
	}

}