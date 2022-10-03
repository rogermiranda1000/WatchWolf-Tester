package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Chain extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Chain(int id) {
		super(id, "Chain");
	}

	private Chain(Chain old) {
		this(old.id);
	}

}