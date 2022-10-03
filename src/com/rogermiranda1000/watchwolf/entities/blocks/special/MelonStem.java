package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MelonStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MelonStem(short id) {
		super(id, "MELON_STEM");
	}

	public MelonStem(int id) {
		this((short) id);
	}

	private MelonStem(MelonStem old) {
		this(old.id);
	}

}