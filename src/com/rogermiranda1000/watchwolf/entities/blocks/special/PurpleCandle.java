package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PurpleCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PurpleCandle(short id) {
		super(id, "PURPLE_CANDLE");
	}

	public PurpleCandle(int id) {
		this((short) id);
	}

	private PurpleCandle(PurpleCandle old) {
		this(old.id);
	}

}