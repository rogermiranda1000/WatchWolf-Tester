package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class SpruceSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public SpruceSign(short id) {
		super(id, "SPRUCE_SIGN");
	}

	public SpruceSign(int id) {
		this((short) id);
	}

	private SpruceSign(SpruceSign old) {
		this(old.id);
	}

}