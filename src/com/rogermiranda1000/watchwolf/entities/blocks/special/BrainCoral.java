package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BrainCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BrainCoral(short id) {
		super(id, "BRAIN_CORAL");
	}

	public BrainCoral(int id) {
		this((short) id);
	}

	private BrainCoral(BrainCoral old) {
		this(old.id);
	}

}