package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadHornCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadHornCoralFan(short id) {
		super(id, "DEAD_HORN_CORAL_FAN");
	}

	public DeadHornCoralFan(int id) {
		this((short) id);
	}

	private DeadHornCoralFan(DeadHornCoralFan old) {
		this(old.id);
	}

}