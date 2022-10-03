package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlackCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BlackCandle(short id) {
		super(id, "BLACK_CANDLE");
	}

	public BlackCandle(int id) {
		this((short) id);
	}

	private BlackCandle(BlackCandle old) {
		this(old.id);
	}

}