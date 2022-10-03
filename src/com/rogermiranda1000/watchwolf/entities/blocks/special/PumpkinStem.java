package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PumpkinStem extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PumpkinStem(short id) {
		super(id, "PUMPKIN_STEM");
	}

	public PumpkinStem(int id) {
		this((short) id);
	}

	private PumpkinStem(PumpkinStem old) {
		this(old.id);
	}

}