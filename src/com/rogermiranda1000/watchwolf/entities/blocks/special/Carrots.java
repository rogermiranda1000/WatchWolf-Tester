package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Carrots extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Carrots(short id) {
		super(id, "CARROTS");
	}

	public Carrots(int id) {
		this((short) id);
	}

	private Carrots(Carrots old) {
		this(old.id);
	}

}