package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlueCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BlueCandle(short id) {
		super(id, "BLUE_CANDLE");
	}

	public BlueCandle(int id) {
		this((short) id);
	}

	private BlueCandle(BlueCandle old) {
		this(old.id);
	}

}