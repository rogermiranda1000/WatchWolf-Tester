package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class SkeletonSkull extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SkeletonSkull(int id) {
		super(id, "SkeletonSkull");
	}

	private SkeletonSkull(SkeletonSkull old) {
		this(old.id);
	}

}