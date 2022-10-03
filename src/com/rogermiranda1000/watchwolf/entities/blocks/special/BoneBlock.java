package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class BoneBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BoneBlock(int id) {
		super(id, "BoneBlock");
	}

	private BoneBlock(BoneBlock old) {
		this(old.id);
	}

}