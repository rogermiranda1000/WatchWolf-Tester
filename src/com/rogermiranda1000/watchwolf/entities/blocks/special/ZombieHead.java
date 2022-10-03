package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class ZombieHead extends Block {

	/*   --- CONSTRUCTORS ---   */
	public ZombieHead(int id) {
		super(id, "ZombieHead");
	}

	private ZombieHead(ZombieHead old) {
		this(old.id);
	}

}