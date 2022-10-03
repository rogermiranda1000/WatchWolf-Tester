package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CreeperHead extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CreeperHead(short id) {
		super(id, "CREEPER_HEAD");
	}

	public CreeperHead(int id) {
		this((short) id);
	}

	private CreeperHead(CreeperHead old) {
		this(old.id);
	}

}