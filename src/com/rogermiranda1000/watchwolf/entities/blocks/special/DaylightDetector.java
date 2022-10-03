package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DaylightDetector extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DaylightDetector(short id) {
		super(id, "DAYLIGHT_DETECTOR");
	}

	public DaylightDetector(int id) {
		this((short) id);
	}

	private DaylightDetector(DaylightDetector old) {
		this(old.id);
	}

}