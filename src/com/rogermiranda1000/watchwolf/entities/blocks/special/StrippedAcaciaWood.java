package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedAcaciaWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedAcaciaWood(short id) {
		super(id, "STRIPPED_ACACIA_WOOD");
	}

	public StrippedAcaciaWood(int id) {
		this((short) id);
	}

	private StrippedAcaciaWood(StrippedAcaciaWood old) {
		this(old.id);
	}

}