package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MagentaBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MagentaBanner(short id) {
		super(id, "MAGENTA_BANNER");
	}

	public MagentaBanner(int id) {
		this((short) id);
	}

	private MagentaBanner(MagentaBanner old) {
		this(old.id);
	}

}