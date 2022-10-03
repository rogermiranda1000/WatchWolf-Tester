package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadBrainCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadBrainCoral(short id) {
		super(id, "DEAD_BRAIN_CORAL");
	}

	public DeadBrainCoral(int id) {
		this((short) id);
	}

	private DeadBrainCoral(DeadBrainCoral old) {
		this(old.id);
	}

}