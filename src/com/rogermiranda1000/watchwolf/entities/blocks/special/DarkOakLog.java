package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DarkOakLog(short id) {
		super(id, "DARK_OAK_LOG");
	}

	public DarkOakLog(int id) {
		this((short) id);
	}

	private DarkOakLog(DarkOakLog old) {
		this(old.id);
	}

}