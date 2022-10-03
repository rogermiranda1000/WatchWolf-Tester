package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Conduit extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Conduit(short id) {
		super(id, "CONDUIT");
	}

	public Conduit(int id) {
		this((short) id);
	}

	private Conduit(Conduit old) {
		this(old.id);
	}

}