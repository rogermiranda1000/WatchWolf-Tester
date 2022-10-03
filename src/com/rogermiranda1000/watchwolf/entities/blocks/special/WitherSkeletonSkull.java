package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WitherSkeletonSkull extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WitherSkeletonSkull(short id) {
		super(id, "WITHER_SKELETON_SKULL");
	}

	public WitherSkeletonSkull(int id) {
		this((short) id);
	}

	private WitherSkeletonSkull(WitherSkeletonSkull old) {
		this(old.id);
	}

}