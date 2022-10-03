package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CrimsonStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CrimsonStem(short id) {
		super(id, "CRIMSON_STEM");
	}

	public CrimsonStem(int id) {
		this((short) id);
	}

	private CrimsonStem(CrimsonStem old) {
		this(old.id);
	}

}