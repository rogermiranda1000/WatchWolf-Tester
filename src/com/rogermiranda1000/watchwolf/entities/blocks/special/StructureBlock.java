package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class StructureBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StructureBlock(int id) {
		super(id, "StructureBlock");
	}

	private StructureBlock(StructureBlock old) {
		this(old.id);
	}

}