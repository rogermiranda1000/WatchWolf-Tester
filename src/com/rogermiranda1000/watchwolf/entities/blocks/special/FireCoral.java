package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class FireCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public FireCoral(short id) {
		super(id, "FIRE_CORAL");
	}

	public FireCoral(int id) {
		this((short) id);
	}

	private FireCoral(FireCoral old) {
		this(old.id);
	}

}