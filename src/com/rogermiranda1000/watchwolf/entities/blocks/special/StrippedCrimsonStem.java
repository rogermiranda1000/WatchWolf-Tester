package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedCrimsonStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedCrimsonStem(short id) {
		super(id, "STRIPPED_CRIMSON_STEM");
	}

	public StrippedCrimsonStem(int id) {
		this((short) id);
	}

	private StrippedCrimsonStem(StrippedCrimsonStem old) {
		this(old.id);
	}

}