package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Composter extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Composter(short id) {
		super(id, "COMPOSTER");
	}

	public Composter(int id) {
		this((short) id);
	}

	private Composter(Composter old) {
		this(old.id);
	}

}