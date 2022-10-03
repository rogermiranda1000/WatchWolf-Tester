package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedJungleWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedJungleWood(short id) {
		super(id, "STRIPPED_JUNGLE_WOOD");
	}

	public StrippedJungleWood(int id) {
		this((short) id);
	}

	private StrippedJungleWood(StrippedJungleWood old) {
		this(old.id);
	}

}