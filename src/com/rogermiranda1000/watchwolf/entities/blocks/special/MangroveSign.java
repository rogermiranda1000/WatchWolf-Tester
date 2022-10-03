package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangroveSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangroveSign(short id) {
		super(id, "MANGROVE_SIGN");
	}

	public MangroveSign(int id) {
		this((short) id);
	}

	private MangroveSign(MangroveSign old) {
		this(old.id);
	}

}