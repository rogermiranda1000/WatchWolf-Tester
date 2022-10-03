package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BubbleCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BubbleCoralFan(short id) {
		super(id, "BUBBLE_CORAL_FAN");
	}

	public BubbleCoralFan(int id) {
		this((short) id);
	}

	private BubbleCoralFan(BubbleCoralFan old) {
		this(old.id);
	}

}