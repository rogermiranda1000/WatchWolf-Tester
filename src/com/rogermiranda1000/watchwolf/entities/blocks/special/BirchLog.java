package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BirchLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BirchLog(short id) {
		super(id, "BIRCH_LOG");
	}

	public BirchLog(int id) {
		this((short) id);
	}

	private BirchLog(BirchLog old) {
		this(old.id);
	}

}