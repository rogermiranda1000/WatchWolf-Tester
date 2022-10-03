package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SculkShrieker extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SculkShrieker(short id) {
		super(id, "SCULK_SHRIEKER");
	}

	public SculkShrieker(int id) {
		this((short) id);
	}

	private SculkShrieker(SculkShrieker old) {
		this(old.id);
	}

}