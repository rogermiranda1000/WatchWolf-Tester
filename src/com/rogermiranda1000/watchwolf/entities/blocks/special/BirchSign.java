package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BirchSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BirchSign(short id) {
		super(id, "BIRCH_SIGN");
	}

	public BirchSign(int id) {
		this((short) id);
	}

	private BirchSign(BirchSign old) {
		this(old.id);
	}

}