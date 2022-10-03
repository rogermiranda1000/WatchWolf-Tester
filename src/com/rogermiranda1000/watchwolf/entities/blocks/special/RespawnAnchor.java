package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class RespawnAnchor extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RespawnAnchor(int id) {
		super(id, "RespawnAnchor");
	}

	private RespawnAnchor(RespawnAnchor old) {
		this(old.id);
	}

}