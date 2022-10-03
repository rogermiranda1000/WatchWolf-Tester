package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Potatoes extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Potatoes(short id) {
		super(id, "POTATOES");
	}

	public Potatoes(int id) {
		this((short) id);
	}

	private Potatoes(Potatoes old) {
		this(old.id);
	}

}