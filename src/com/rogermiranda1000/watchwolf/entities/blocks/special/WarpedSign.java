package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WarpedSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedSign(short id) {
		super(id, "WARPED_SIGN");
	}

	public WarpedSign(int id) {
		this((short) id);
	}

	private WarpedSign(WarpedSign old) {
		this(old.id);
	}

}