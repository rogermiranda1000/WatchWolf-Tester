package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedJungleLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedJungleLog(short id) {
		super(id, "STRIPPED_JUNGLE_LOG");
	}

	public StrippedJungleLog(int id) {
		this((short) id);
	}

	private StrippedJungleLog(StrippedJungleLog old) {
		this(old.id);
	}

}