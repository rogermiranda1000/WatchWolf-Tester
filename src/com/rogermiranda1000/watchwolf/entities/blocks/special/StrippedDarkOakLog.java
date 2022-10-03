package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedDarkOakLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedDarkOakLog(short id) {
		super(id, "STRIPPED_DARK_OAK_LOG");
	}

	public StrippedDarkOakLog(int id) {
		this((short) id);
	}

	private StrippedDarkOakLog(StrippedDarkOakLog old) {
		this(old.id);
	}

}