package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JungleLeaves extends Block {

	/*   --- CONSTRUCTORS ---   */
	public JungleLeaves(short id) {
		super(id, "JUNGLE_LEAVES");
	}

	public JungleLeaves(int id) {
		this((short) id);
	}

	private JungleLeaves(JungleLeaves old) {
		this(old.id);
	}

}