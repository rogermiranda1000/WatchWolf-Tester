package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JungleWood extends Block {

	/*   --- CONSTRUCTORS ---   */
	public JungleWood(short id) {
		super(id, "JUNGLE_WOOD");
	}

	public JungleWood(int id) {
		this((short) id);
	}

	private JungleWood(JungleWood old) {
		this(old.id);
	}

}