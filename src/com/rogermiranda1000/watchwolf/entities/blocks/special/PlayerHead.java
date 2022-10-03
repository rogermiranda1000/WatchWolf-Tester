package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class PlayerHead extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PlayerHead(int id) {
		super(id, "PlayerHead");
	}

	private PlayerHead(PlayerHead old) {
		this(old.id);
	}

}