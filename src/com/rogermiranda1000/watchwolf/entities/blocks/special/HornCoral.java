package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class HornCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public HornCoral(short id) {
		super(id, "HORN_CORAL");
	}

	public HornCoral(int id) {
		this((short) id);
	}

	private HornCoral(HornCoral old) {
		this(old.id);
	}

}