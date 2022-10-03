package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StructureBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StructureBlock(short id) {
		super(id, "STRUCTURE_BLOCK");
	}

	public StructureBlock(int id) {
		this((short) id);
	}

	private StructureBlock(StructureBlock old) {
		this(old.id);
	}

}