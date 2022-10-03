package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadTubeCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadTubeCoral(short id) {
		super(id, "DEAD_TUBE_CORAL");
	}

	public DeadTubeCoral(int id) {
		this((short) id);
	}

	private DeadTubeCoral(DeadTubeCoral old) {
		this(old.id);
	}

}