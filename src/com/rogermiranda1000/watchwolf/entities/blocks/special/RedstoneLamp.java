package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedstoneLamp extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedstoneLamp(short id) {
		super(id, "REDSTONE_LAMP");
	}

	public RedstoneLamp(int id) {
		this((short) id);
	}

	private RedstoneLamp(RedstoneLamp old) {
		this(old.id);
	}

}