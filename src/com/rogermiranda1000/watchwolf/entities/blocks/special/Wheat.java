package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Wheat extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Wheat(short id) {
		super(id, "WHEAT");
	}

	public Wheat(int id) {
		this((short) id);
	}

	private Wheat(Wheat old) {
		this(old.id);
	}

}