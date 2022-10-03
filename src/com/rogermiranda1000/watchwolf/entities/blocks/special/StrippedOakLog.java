package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedOakLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedOakLog(short id) {
		super(id, "STRIPPED_OAK_LOG");
	}

	public StrippedOakLog(int id) {
		this((short) id);
	}

	private StrippedOakLog(StrippedOakLog old) {
		this(old.id);
	}

}