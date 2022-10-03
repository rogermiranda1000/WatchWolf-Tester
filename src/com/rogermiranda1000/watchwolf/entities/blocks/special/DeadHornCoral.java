package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadHornCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadHornCoral(short id) {
		super(id, "DEAD_HORN_CORAL");
	}

	public DeadHornCoral(int id) {
		this((short) id);
	}

	private DeadHornCoral(DeadHornCoral old) {
		this(old.id);
	}

}