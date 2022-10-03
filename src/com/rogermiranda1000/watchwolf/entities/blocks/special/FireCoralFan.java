package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class FireCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public FireCoralFan(short id) {
		super(id, "FIRE_CORAL_FAN");
	}

	public FireCoralFan(int id) {
		this((short) id);
	}

	private FireCoralFan(FireCoralFan old) {
		this(old.id);
	}

}