package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedBirchLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedBirchLog(short id) {
		super(id, "STRIPPED_BIRCH_LOG");
	}

	public StrippedBirchLog(int id) {
		this((short) id);
	}

	private StrippedBirchLog(StrippedBirchLog old) {
		this(old.id);
	}

}