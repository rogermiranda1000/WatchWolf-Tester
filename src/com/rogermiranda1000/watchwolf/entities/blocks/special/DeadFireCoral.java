package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadFireCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadFireCoral(short id) {
		super(id, "DEAD_FIRE_CORAL");
	}

	public DeadFireCoral(int id) {
		this((short) id);
	}

	private DeadFireCoral(DeadFireCoral old) {
		this(old.id);
	}

}