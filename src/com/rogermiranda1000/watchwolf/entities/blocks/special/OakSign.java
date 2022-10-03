package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OakSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OakSign(short id) {
		super(id, "OAK_SIGN");
	}

	public OakSign(int id) {
		this((short) id);
	}

	private OakSign(OakSign old) {
		this(old.id);
	}

}