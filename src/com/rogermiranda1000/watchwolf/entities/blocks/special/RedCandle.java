package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedCandle(short id) {
		super(id, "RED_CANDLE");
	}

	public RedCandle(int id) {
		this((short) id);
	}

	private RedCandle(RedCandle old) {
		this(old.id);
	}

}