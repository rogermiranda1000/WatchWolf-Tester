package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DragonHead extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DragonHead(short id) {
		super(id, "DRAGON_HEAD");
	}

	public DragonHead(int id) {
		this((short) id);
	}

	private DragonHead(DragonHead old) {
		this(old.id);
	}

}