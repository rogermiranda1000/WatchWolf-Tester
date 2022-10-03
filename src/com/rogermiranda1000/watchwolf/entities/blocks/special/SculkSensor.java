package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SculkSensor extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SculkSensor(short id) {
		super(id, "SCULK_SENSOR");
	}

	public SculkSensor(int id) {
		this((short) id);
	}

	private SculkSensor(SculkSensor old) {
		this(old.id);
	}

}