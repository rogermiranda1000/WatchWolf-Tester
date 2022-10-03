package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CrimsonSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CrimsonSign(short id) {
		super(id, "CRIMSON_SIGN");
	}

	public CrimsonSign(int id) {
		this((short) id);
	}

	private CrimsonSign(CrimsonSign old) {
		this(old.id);
	}

}