package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class FloweringAzaleaLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public FloweringAzaleaLeaves(short id) {
		super(id, "FLOWERING_AZALEA_LEAVES");
	}

	public FloweringAzaleaLeaves(int id) {
		this((short) id);
	}

	private FloweringAzaleaLeaves(FloweringAzaleaLeaves old) {
		this(old.id);
	}

}