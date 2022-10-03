package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadBrainCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadBrainCoralFan(short id) {
		super(id, "DEAD_BRAIN_CORAL_FAN");
	}

	public DeadBrainCoralFan(int id) {
		this((short) id);
	}

	private DeadBrainCoralFan(DeadBrainCoralFan old) {
		this(old.id);
	}

}