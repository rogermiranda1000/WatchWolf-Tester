package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedMangroveLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedMangroveLog(short id) {
		super(id, "STRIPPED_MANGROVE_LOG");
	}

	public StrippedMangroveLog(int id) {
		this((short) id);
	}

	private StrippedMangroveLog(StrippedMangroveLog old) {
		this(old.id);
	}

}