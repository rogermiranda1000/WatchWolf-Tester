package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SkeletonSkull extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SkeletonSkull(short id) {
		super(id, "SKELETON_SKULL");
	}

	public SkeletonSkull(int id) {
		this((short) id);
	}

	private SkeletonSkull(SkeletonSkull old) {
		this(old.id);
	}

}