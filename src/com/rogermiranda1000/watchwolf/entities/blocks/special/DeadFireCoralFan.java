package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadFireCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DeadFireCoralFan(short id) {
		super(id, "DEAD_FIRE_CORAL_FAN");
	}

	public DeadFireCoralFan(int id) {
		this((short) id);
	}

	private DeadFireCoralFan(DeadFireCoralFan old) {
		this(old.id);
	}

}