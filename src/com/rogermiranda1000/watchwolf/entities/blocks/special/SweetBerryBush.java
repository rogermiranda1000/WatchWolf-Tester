package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SweetBerryBush extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SweetBerryBush(short id) {
		super(id, "SWEET_BERRY_BUSH");
	}

	public SweetBerryBush(int id) {
		this((short) id);
	}

	private SweetBerryBush(SweetBerryBush old) {
		this(old.id);
	}

}