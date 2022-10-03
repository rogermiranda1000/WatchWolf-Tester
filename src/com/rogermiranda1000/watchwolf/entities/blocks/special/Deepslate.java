package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Deepslate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Deepslate(short id) {
		super(id, "DEEPSLATE");
	}

	public Deepslate(int id) {
		this((short) id);
	}

	private Deepslate(Deepslate old) {
		this(old.id);
	}

}