package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class HornCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public HornCoralFan(short id) {
		super(id, "HORN_CORAL_FAN");
	}

	public HornCoralFan(int id) {
		this((short) id);
	}

	private HornCoralFan(HornCoralFan old) {
		this(old.id);
	}

}