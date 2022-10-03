package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadTubeCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadTubeCoralFan(short id) {
		super(id, "DEAD_TUBE_CORAL_FAN");
	}

	public DeadTubeCoralFan(int id) {
		this((short) id);
	}

	private DeadTubeCoralFan(DeadTubeCoralFan old) {
		this(old.id);
	}

}