package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class TurtleEgg extends Block {

	/*   --- CONSTRUCTORS ---   */
	public TurtleEgg(int id) {
		super(id, "TurtleEgg");
	}

	private TurtleEgg(TurtleEgg old) {
		this(old.id);
	}

}