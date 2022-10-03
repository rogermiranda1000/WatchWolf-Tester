package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class TubeCoralFan extends Block {

	/*   --- CONSTRUCTORS ---   */
	public TubeCoralFan(short id) {
		super(id, "TUBE_CORAL_FAN");
	}

	public TubeCoralFan(int id) {
		this((short) id);
	}

	private TubeCoralFan(TubeCoralFan old) {
		this(old.id);
	}

}