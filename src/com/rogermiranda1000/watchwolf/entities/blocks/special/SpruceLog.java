package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SpruceLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SpruceLog(short id) {
		super(id, "SPRUCE_LOG");
	}

	public SpruceLog(int id) {
		this((short) id);
	}

	private SpruceLog(SpruceLog old) {
		this(old.id);
	}

}