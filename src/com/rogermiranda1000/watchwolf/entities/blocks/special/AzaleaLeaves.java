package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AzaleaLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AzaleaLeaves(short id) {
		super(id, "AZALEA_LEAVES");
	}

	public AzaleaLeaves(int id) {
		this((short) id);
	}

	private AzaleaLeaves(AzaleaLeaves old) {
		this(old.id);
	}

}