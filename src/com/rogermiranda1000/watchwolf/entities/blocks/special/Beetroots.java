package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Beetroots extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Beetroots(short id) {
		super(id, "BEETROOTS");
	}

	public Beetroots(int id) {
		this((short) id);
	}

	private Beetroots(Beetroots old) {
		this(old.id);
	}

}