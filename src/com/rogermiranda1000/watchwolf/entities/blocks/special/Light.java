package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Light extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Light(short id) {
		super(id, "LIGHT");
	}

	public Light(int id) {
		this((short) id);
	}

	private Light(Light old) {
		this(old.id);
	}

}