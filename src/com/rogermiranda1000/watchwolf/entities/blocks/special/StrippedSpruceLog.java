package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedSpruceLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedSpruceLog(short id) {
		super(id, "STRIPPED_SPRUCE_LOG");
	}

	public StrippedSpruceLog(int id) {
		this((short) id);
	}

	private StrippedSpruceLog(StrippedSpruceLog old) {
		this(old.id);
	}

}