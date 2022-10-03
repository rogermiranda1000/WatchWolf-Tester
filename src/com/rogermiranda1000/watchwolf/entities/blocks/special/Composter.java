package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Composter extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Composter(int id) {
		super(id, "Composter");
	}

	private Composter(Composter old) {
		this(old.id);
	}

}