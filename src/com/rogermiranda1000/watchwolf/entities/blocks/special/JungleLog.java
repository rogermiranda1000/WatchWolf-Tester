package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JungleLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public JungleLog(short id) {
		super(id, "JUNGLE_LOG");
	}

	public JungleLog(int id) {
		this((short) id);
	}

	private JungleLog(JungleLog old) {
		this(old.id);
	}

}