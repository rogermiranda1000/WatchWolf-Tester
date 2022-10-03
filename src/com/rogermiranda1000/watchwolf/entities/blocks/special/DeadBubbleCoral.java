package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadBubbleCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadBubbleCoral(short id) {
		super(id, "DEAD_BUBBLE_CORAL");
	}

	public DeadBubbleCoral(int id) {
		this((short) id);
	}

	private DeadBubbleCoral(DeadBubbleCoral old) {
		this(old.id);
	}

}