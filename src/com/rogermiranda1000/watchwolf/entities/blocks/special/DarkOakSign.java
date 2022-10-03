package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DarkOakSign(short id) {
		super(id, "DARK_OAK_SIGN");
	}

	public DarkOakSign(int id) {
		this((short) id);
	}

	private DarkOakSign(DarkOakSign old) {
		this(old.id);
	}

}