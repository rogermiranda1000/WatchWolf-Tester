package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AcaciaSign(short id) {
		super(id, "ACACIA_SIGN");
	}

	public AcaciaSign(int id) {
		this((short) id);
	}

	private AcaciaSign(AcaciaSign old) {
		this(old.id);
	}

}