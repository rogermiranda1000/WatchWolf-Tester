package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CaveVines extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CaveVines(short id) {
		super(id, "CAVE_VINES");
	}

	public CaveVines(int id) {
		this((short) id);
	}

	private CaveVines(CaveVines old) {
		this(old.id);
	}

}