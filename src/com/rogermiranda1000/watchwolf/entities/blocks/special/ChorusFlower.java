package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class ChorusFlower extends Block {

	/*   --- CONSTRUCTORS ---   */
	public ChorusFlower(short id) {
		super(id, "CHORUS_FLOWER");
	}

	public ChorusFlower(int id) {
		this((short) id);
	}

	private ChorusFlower(ChorusFlower old) {
		this(old.id);
	}

}