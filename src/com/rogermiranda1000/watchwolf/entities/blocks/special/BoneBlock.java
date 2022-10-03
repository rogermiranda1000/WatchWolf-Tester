package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BoneBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BoneBlock(short id) {
		super(id, "BONE_BLOCK");
	}

	public BoneBlock(int id) {
		this((short) id);
	}

	private BoneBlock(BoneBlock old) {
		this(old.id);
	}

}