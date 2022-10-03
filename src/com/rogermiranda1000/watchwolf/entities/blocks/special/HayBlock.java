package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class HayBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public HayBlock(int id) {
		super(id, "HayBlock");
	}

	private HayBlock(HayBlock old) {
		this(old.id);
	}

}