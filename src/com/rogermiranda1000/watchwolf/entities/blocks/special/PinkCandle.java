package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PinkCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public PinkCandle(short id) {
		super(id, "PINK_CANDLE");
	}

	public PinkCandle(int id) {
		this((short) id);
	}

	private PinkCandle(PinkCandle old) {
		this(old.id);
	}

}