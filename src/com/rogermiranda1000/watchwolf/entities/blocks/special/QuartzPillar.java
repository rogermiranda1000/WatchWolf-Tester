package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class QuartzPillar extends Block {

	/*   --- CONSTRUCTORS ---   */
	public QuartzPillar(short id) {
		super(id, "QUARTZ_PILLAR");
	}

	public QuartzPillar(int id) {
		this((short) id);
	}

	private QuartzPillar(QuartzPillar old) {
		this(old.id);
	}

}