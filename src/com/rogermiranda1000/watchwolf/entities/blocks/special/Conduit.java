package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Conduit extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Conduit(int id) {
		super(id, "Conduit");
	}

	private Conduit(Conduit old) {
		this(old.id);
	}

}