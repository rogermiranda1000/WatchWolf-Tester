package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadBubbleCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadBubbleCoralFan(short id) {
		super(id, "DEAD_BUBBLE_CORAL_FAN");
	}

	public DeadBubbleCoralFan(int id) {
		this((short) id);
	}

	private DeadBubbleCoralFan(DeadBubbleCoralFan old) {
		this(old.id);
	}

}