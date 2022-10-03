package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AcaciaWood(short id) {
		super(id, "ACACIA_WOOD");
	}

	public AcaciaWood(int id) {
		this((short) id);
	}

	private AcaciaWood(AcaciaWood old) {
		this(old.id);
	}

}