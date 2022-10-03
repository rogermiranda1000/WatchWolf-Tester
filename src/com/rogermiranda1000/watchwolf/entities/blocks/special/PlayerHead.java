package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PlayerHead extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PlayerHead(short id) {
		super(id, "PLAYER_HEAD");
	}

	public PlayerHead(int id) {
		this((short) id);
	}

	private PlayerHead(PlayerHead old) {
		this(old.id);
	}

}