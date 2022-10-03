package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class RedstoneLamp extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedstoneLamp(int id) {
		super(id, "RedstoneLamp");
	}

	private RedstoneLamp(RedstoneLamp old) {
		this(old.id);
	}

}